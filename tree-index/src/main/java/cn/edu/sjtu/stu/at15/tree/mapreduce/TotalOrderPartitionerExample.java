package cn.edu.sjtu.stu.at15.tree.mapreduce;

import java.io.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.partition.*;
import org.apache.hadoop.mapreduce.lib.reduce.*;
import org.apache.hadoop.util.*;

/**
 * Demonstrates how to use Total Order Partitioner on Word Count.
 */
public class TotalOrderPartitionerExample {

    public static class WordCount extends Configured implements Tool {

        private final static int REDUCE_TASKS = 8;

        public static void main(String[] args) throws Exception {
            int exitCode = ToolRunner.run(new WordCount(), args);
            System.exit(exitCode);
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public int run(String[] args) throws Exception {
            // Check arguments.
            if (args.length != 2) {
                String usage =
                        "Usage: " +
                                "hadoop jar TotalOrderPartitionerExample$WordCount " +
                                "<input dir> <output dir>\n";
                System.out.printf(usage);
                System.exit(-1);
            }

            String jobName = "WordCount";
            String mapJobName = jobName + "-Map";
            String reduceJobName = jobName + "-Reduce";

            // Get user args.
            String inputDir = args[0];
            String outputDir = args[1];

            // Define input path and output path.
            Path mapInputPath = new Path(inputDir);
            Path mapOutputPath = new Path(outputDir + "-inter");
            Path reduceOutputPath = new Path(outputDir);

            // Define partition file path.
            Path partitionPath = new Path(outputDir + "-part.lst");

            // Configure map-only job for sampling.
            Job mapJob = new Job(getConf());
            mapJob.setJobName(mapJobName);
            mapJob.setJarByClass(WordCount.class);
            mapJob.setMapperClass(WordMapper.class);
            mapJob.setNumReduceTasks(0);
            mapJob.setOutputKeyClass(Text.class);
            mapJob.setOutputValueClass(IntWritable.class);
            TextInputFormat.setInputPaths(mapJob, mapInputPath);

            // Set the output format to a sequence file.
            mapJob.setOutputFormatClass(SequenceFileOutputFormat.class);
            SequenceFileOutputFormat.setOutputPath(mapJob, mapOutputPath);

            // Submit the map-only job.
            int exitCode = mapJob.waitForCompletion(true) ? 0 : 1;
            if (exitCode != 0) { return exitCode; }

            // Set up the second job, the reduce-only.
            Job reduceJob = new Job(getConf());
            reduceJob.setJobName(reduceJobName);
            reduceJob.setJarByClass(WordCount.class);

            // Set the input to the previous job's output.
            reduceJob.setInputFormatClass(SequenceFileInputFormat.class);
            SequenceFileInputFormat.setInputPaths(reduceJob, mapOutputPath);

            // Set the output path to the final output path.
            TextOutputFormat.setOutputPath(reduceJob, reduceOutputPath);

            // Use identity mapper for key/value pairs in SequenceFile.
            reduceJob.setReducerClass(IntSumReducer.class);
            reduceJob.setMapOutputKeyClass(Text.class);
            reduceJob.setMapOutputValueClass(IntWritable.class);
            reduceJob.setOutputKeyClass(Text.class);
            reduceJob.setOutputValueClass(IntWritable.class);
            reduceJob.setNumReduceTasks(REDUCE_TASKS);

            // Use Total Order Partitioner.
            reduceJob.setPartitionerClass(TotalOrderPartitioner.class);

            // Generate partition file from map-only job's output.
            TotalOrderPartitioner.setPartitionFile(
                    reduceJob.getConfiguration(), partitionPath);
            InputSampler.writePartitionFile(reduceJob, new InputSampler.RandomSampler(
                    1, 10000));

            // Submit the reduce job.
            return reduceJob.waitForCompletion(true) ? 0 : 2;
        }
    }

    public static class WordMapper extends
            Mapper<LongWritable, Text, Text, IntWritable> {
        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            for (String word : line.split("\\W+")) {
                if (word.length() == 0) { continue; }
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

}