package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
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
        // config
        int numReducers = 5;

        // job
        Job job = Job.getInstance(getConf());
        job.setJobName("index");
        job.setJarByClass(IndexDriver.class);

//        Path inputPath = new Path(PathConstant.SORTED_FILE_LIST);
        Path inputPath = new Path(PathConstant.SORT_META_OUTPUT);
        Path outputPath = new Path(PathConstant.INDEX_OUTPUT);

        // set mapper
        job.setMapperClass(IndexMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, inputPath);

        // set the reducer
        job.setNumReduceTasks(numReducers);
        job.setReducerClass(IndexReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, outputPath);

        // TODO: need a custom partitioner, otherwise the meta file won't match, could use another mapper

        // clean up the old output path
        outputPath.getFileSystem(job.getConfiguration()).delete(outputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
