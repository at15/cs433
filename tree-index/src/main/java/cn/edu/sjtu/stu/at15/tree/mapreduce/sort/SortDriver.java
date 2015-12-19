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

        job.setInputFormatClass(IntegerKeyFileInputFormat.class);

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
        // NOTE: sampler sample based on input format, so when you use KeyValueTextInputFormat
        // The sampler will treat key as text, and the partition will compare key using compare string method
        // ie, 950 < 98 , 10 < 2, when compare as string
        // So you MUST have your input format using the right Key type, ie, you want to partition based on a
        // integer value, like user id. you need to have your input format has key type Integer
        // so there are two way to do this
        // 1. The easy way, use TextInputFormat, because its Key is LongWritable, so you use Long all the way instead
        //    of Int, because the MapOutKeyClass MUST match the input source key class
        // 2. The Doubi way, write a custom input Format like what I did, this not so bad, because it split using tab and
        //    transform the first column to integer, but since you can also do it in mapper, it's really not a meaningful
        //    thing
        InputSampler.writePartitionFile(job, new InputSampler.RandomSampler(
                1, 10000));

        job.setJarByClass(SortDriver.class);

        output.getFileSystem(conf).delete(output, true);

        job.waitForCompletion(true);
    }
}
