package cn.edu.sjtu.stu.at15.hbase;


import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-11-29.
 */
public class Seeder {
    private static final Logger LOGGER = LoggerFactory.getLogger(Seeder.class);

    public static void main(String[] args) throws Exception {
        // Create a configuration that connects to a local HBase
        Configuration conf = HBaseConfiguration.create();

        HTable hTable = new HTable(conf, "contacts");

        Put p = new Put(Bytes.toBytes("1"));

        p.add(Bytes.toBytes("info"),
                Bytes.toBytes("email"),Bytes.toBytes("jack@123.com"));
        p.add(Bytes.toBytes("info"),
                Bytes.toBytes("name"),Bytes.toBytes("jack"));

        hTable.put(p);
        hTable.close();

    }
}
