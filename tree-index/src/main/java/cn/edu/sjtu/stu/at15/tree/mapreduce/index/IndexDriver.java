package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by at15 on 15-12-20.
 */
public class IndexDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(IndexDriver.class);

    public int run(String args[]) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJobName("index");
        job.setJarByClass(IndexDriver.class);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
