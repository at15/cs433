package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.partition.*;
import org.apache.hadoop.util.*;

/**
 * Created by at15 on 15-12-19.
 * <p/>
 * sort input data by certain column
 */
public class SortDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(SortDriver.class);

    public int run(String[] args) throws Exception {
        // configs
        int numReducers = 5;

        Job job = Job.getInstance(getConf());
        job.setJobName("sort");
        job.setJarByClass(SortDriver.class);

        // define the path
        Path inputPath = new Path(PathConstant.PRE_SORT_OUTPUT);
        Path partitionFilePath = new Path(PathConstant.SORT_PARTITION_FILE);
        Path outputPath = new Path(PathConstant.SORT_OUTPUT);
        // TODO: log the paths out

        // define the mapper
        // use the identity mapper, which is the default implementation
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);
        SequenceFileInputFormat.setInputPaths(job, inputPath);

        // define the reducer
        // use the identity reducer, which is the default, because we only need sort and partition
        // WORKING: use reducer to generate meta data
        job.setReducerClass(SortReducer.class);
        job.setNumReduceTasks(numReducers);
        // use text for debug, use sequence is faster I guess
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, outputPath);

        // set partition
        job.setPartitionerClass(TotalOrderPartitioner.class);
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), partitionFilePath);

        // set the sampler
        InputSampler.writePartitionFile(job, new InputSampler.RandomSampler(
                1, 10000));

//        // set multiple output
//        MultipleOutputs.addNamedOutput(job, "meta", TextOutputFormat.class,
//                Text.class, Text.class);

        // clean up the old output path
        outputPath.getFileSystem(job.getConfiguration()).delete(outputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
