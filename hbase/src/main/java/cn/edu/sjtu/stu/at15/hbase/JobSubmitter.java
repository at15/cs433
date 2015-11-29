package cn.edu.sjtu.stu.at15.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.MultiTableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by at15 on 15-11-29.
 */
public class JobSubmitter {
    // copied from https://github.com/apache/hbase/blob/master/hbase-server/src/main/java/org/apache/hadoop/hbase/mapreduce/TableMapReduceUtil.java
    /**
     * Writes the given scan into a Base64 encoded string.
     *
     * @param scan The scan to write out.
     * @return The scan saved in a Base64 encoded string.
     * @throws IOException When writing the scan fails.
     */
    private static String convertScanToString(Scan scan) throws IOException {
        ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
        return Base64.encodeBytes(proto.toByteArray());
    }

    public void run() throws Exception {
        String tableName = "contacts";

        Configuration config = HBaseConfiguration.create();

        Scan scan = new Scan();
        scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
        scan.setCacheBlocks(false);  // don't set to true for MR jobs

        config.set(TableInputFormat.SCAN, convertScanToString(scan));
        config.set(TableInputFormat.INPUT_TABLE, tableName);

        Job job = new Job(config, "index builder");
        job.setJarByClass(JobSubmitter.class);
        job.setMapperClass(IndexMapper.class);
        job.setNumReduceTasks(0);
        job.setInputFormatClass(TableInputFormat.class);
        job.setOutputFormatClass(MultiTableOutputFormat.class);

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
