package cn.edu.sjtu.stu.at15.hbase;


import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-11-29.
 *
 * @TODO: the insert is not saved, or got RetriesExhaustedWithDetailsException for wrong column family name etc
 */
public class Seeder {
    private static final Logger LOGGER = LoggerFactory.getLogger(Seeder.class);

    public static void main(String[] args) throws Exception {
        // Create a configuration that connects to a local HBase
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.master", "localhost:60000");

//        // Instantiating HTable class
//        HTable hTable = new HTable(conf, "emp");
//
//        Put put1 = new Put(Bytes.toBytes("row1"));
//        put1.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
//
//        hTable.flushCommits();
//
//        Get get = new Get(Bytes.toBytes("row1"));
//        Result result = hTable.get(get);
//
//        LOGGER.debug(result.toString());
//        hTable.close();

        // Instantiating HTable class
        HTable hTable = new HTable(conf, "emp");

        // Instantiating Put class
        // accepts a row name.
        Put p = new Put(Bytes.toBytes("row1"));

        // adding values using add() method
        // accepts column family name, qualifier/row name ,value
        p.add(Bytes.toBytes("personal"),
                Bytes.toBytes("name"),Bytes.toBytes("raju"));

        p.add(Bytes.toBytes("personal"),
                Bytes.toBytes("city"),Bytes.toBytes("hyderabad"));

        p.add(Bytes.toBytes("professional"),Bytes.toBytes("designation"),
                Bytes.toBytes("manager"));

        p.add(Bytes.toBytes("professional"),Bytes.toBytes("salary"),
                Bytes.toBytes("50000"));

        // Saving the put Instance to the HTable.
        hTable.put(p);
        System.out.println("data inserted");

        // closing HTable
        hTable.close();
    }
}
