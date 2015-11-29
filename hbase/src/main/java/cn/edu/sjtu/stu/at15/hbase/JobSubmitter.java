package cn.edu.sjtu.stu.at15.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by at15 on 15-11-29.
 */
public class JobSubmitter {
    public void run() throws Exception {
        String tableName = "contacts";

        Configuration config = HBaseConfiguration.create();
        Job job = new Job(config, "ExampleRead");
        job.setJarByClass(JobSubmitter.class);

        Scan scan = new Scan();
        scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
        scan.setCacheBlocks(false);  // don't set to true for MR jobs

        TableMapReduceUtil.initTableMapperJob(
                tableName,        // input HBase table name
                scan,             // Scan instance to control CF and attribute selection
                IndexMapper.class,   // mapper
                null,             // mapper output key
                null,             // mapper output value
                job);
        job.setOutputFormatClass(NullOutputFormat.class);   // because we aren't emitting anything from mapper

        boolean b = job.waitForCompletion(true);
        if (!b) {
            throw new IOException("error with job!");
        }
    }

    public static void main(String[] args) throws Exception {
        JobSubmitter jobSubmitter = new JobSubmitter();
        jobSubmitter.run();
    }
}
