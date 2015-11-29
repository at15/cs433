package cn.edu.sjtu.stu.at15.hbase;


import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by at15 on 15-11-29.
 */
public class IndexMapper extends Mapper<ImmutableBytesWritable, Result, ImmutableBytesWritable, Put> {
    public static final byte[] INDEX_COLUMN = Bytes.toBytes("index");
    public static final byte[] INDEX_QUALIFIER = Bytes.toBytes("row");

    // qualifier, index-table-name
    private Map<String, String> indexes;
    private byte[] family;

    // list the index tables we are going to create
    protected void setup(Context context) throws IOException,
            InterruptedException {
        indexes = new HashMap();
        indexes.put("name", "contacts-name-index");

        family = Bytes.toBytes("info");
    }

    public void map(ImmutableBytesWritable rowKey, Result result, Context context)
            throws IOException, InterruptedException {

        for (Map.Entry<String, String> index : indexes.entrySet()) {
            String qualifier = index.getKey();
            ImmutableBytesWritable tableName = new ImmutableBytesWritable(Bytes.toBytes(index.getValue()));

            byte[] value = result.getValue(family, Bytes.toBytes(qualifier));
            if (value != null) {
                Put put = new Put(value);
                put.addColumn(INDEX_COLUMN, INDEX_QUALIFIER, rowKey.get());
                context.write(tableName, put);
            }
        }
    }
}