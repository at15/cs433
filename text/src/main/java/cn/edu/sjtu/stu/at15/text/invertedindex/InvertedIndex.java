package cn.edu.sjtu.stu.at15.text.invertedindex;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by gpl on 15/11/7.
 */
public class InvertedIndex {
    private static String jobName = "invertedindex";

    public static void main(String[] args) throws Exception {
        run(args[0], args[1]);
    }

    public static void run(String inputPath, String outputPath) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "InvertedIndex");
        job.setJarByClass(InvertedIndex.class);

        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setCombinerClass(Combine.class);
        job.setReducerClass(Reduce.class);
//        job.setReducerClass(Combine.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
