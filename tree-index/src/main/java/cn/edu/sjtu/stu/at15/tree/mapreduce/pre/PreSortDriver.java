package cn.edu.sjtu.stu.at15.tree.mapreduce.pre;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.partition.*;

/**
 * Created by at15 on 15-12-19.
 */
public class PreSortDriver extends Configured implements Tool {
    // input, table column text separated by tab
    // output,<int,string> use specific column value as key, line as value
    // the key is cast into IntWritable, and use the first column by default
    public int run(String[] args) throws Exception {
        // create the job, NOTE: use getConf()
        // see http://stackoverflow.com/questions/28333080/getting-the-tool-interface-warning-even-though-it-is-implemented
        Job job = Job.getInstance(getConf());
        job.setJobName("pre sort");
        job.setJarByClass(PreSortDriver.class);

        // define the path
        Path mapInputPath = new Path(PathConstant.PRE_SORT_INPUT);
        Path mapOutputPath = new Path(PathConstant.PRE_SORT_OUTPUT);

        // define the mapper
        // TODO: get the column from config
        job.getConfiguration().set(PreSortMapper.COLUMN_INDEX_CONFIG_NAME, "1"); // use the second column
        job.setMapperClass(PreSortMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, mapInputPath);

        // define the reducer, we don't need any reducer
        job.setNumReduceTasks(0);

        // define the output, NOTE: we do not have reducer
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setOutputPath(job, mapOutputPath);

        // clean up the output folder
        mapOutputPath.getFileSystem(getConf()).delete(mapOutputPath, true);

        // run the job and wait until it complete
        return job.waitForCompletion(true) ? 0 : 1;
    }

    //    public static void main(String[] args) throws Exception {
//        int exitCode = ToolRunner.run(new PreSortDriver(), args);
//        System.exit(exitCode);
//    }
}
