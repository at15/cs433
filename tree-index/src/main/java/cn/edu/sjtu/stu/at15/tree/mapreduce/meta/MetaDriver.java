package cn.edu.sjtu.stu.at15.tree.mapreduce.meta;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
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
public class MetaDriver extends Configured implements Tool {
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf());
        job.setJobName("meta");
        job.setJarByClass(MetaDriver.class);

        // define the path
        Path mapInputPath = new Path(PathConstant.INDEX_OUTPUT);
        Path mapOutputPath = new Path(PathConstant.META_OUTPUT);

        // define the mapper
        job.setMapperClass(MetaMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, mapInputPath);

        // define the reducer, identity reducer
        job.setNumReduceTasks(1);

        // output
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        TextOutputFormat.setOutputPath(job, mapOutputPath);

        // clean the old output
        mapOutputPath.getFileSystem(job.getConfiguration()).delete(mapOutputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
