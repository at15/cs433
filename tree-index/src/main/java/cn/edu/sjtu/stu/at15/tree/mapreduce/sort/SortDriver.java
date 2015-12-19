package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Created by at15 on 15-12-19.
 * <p/>
 * sort input data by certain column
 */
public class SortDriver {
    public static final Logger LOGGER = LoggerFactory.getLogger(SortDriver.class);

    public static void main(String[] args) throws Exception {
        SortDriver driver = new SortDriver();
        driver.run();
    }

    public void run() throws Exception {

        int numReducers = 5;
        Path input = new Path("/user/at15/tree-index/input");
        Path partitionFile = new Path("/user/at15/tree-index/part.lst");
        Path output = new Path("/user/at15/tree-index/output");

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "sort data before build index");

        job.setNumReduceTasks(numReducers);

        // TODO: guess I need a mapper to handle the data..., yes
//        job.setInputFormatClass(KeyValueTextInputFormat.class);
//        job.setInputFormatClass(TextInputFormat.class);
        job.setInputFormatClass(IntegerKeyFileInputFormat.class);

//        job.setMapperClass(SortMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        job.setPartitionerClass(TotalOrderPartitioner.class);


        FileInputFormat.setInputPaths(job, input);
        FileOutputFormat.setOutputPath(job, output);

        // http://stackoverflow.com/questions/25051671/totalorderpartitioner-ignores-partition-file-location
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), partitionFile);
        InputSampler.writePartitionFile(job, new InputSampler.RandomSampler(
                1, 10000));

        job.setJarByClass(SortDriver.class);

        output.getFileSystem(conf).delete(output, true);

        job.waitForCompletion(true);
    }
}
