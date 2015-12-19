package cn.edu.sjtu.stu.at15.tree.mapreduce.pre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by at15 on 15-12-19.
 */
public class PreSortMapper extends
        Mapper<LongWritable, Text, IntWritable, Text> {
    private Integer columnIndex = 0;
    private IntWritable keyVal;

    // get the column from configuration
    // TODO: check if the config is working
    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration configuration = context.getConfiguration();
        columnIndex = Integer.parseInt(configuration.get("presort.column.index", "0"));
        keyVal = new IntWritable();
    }

    // output specific column as key
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] columns = line.split("\\t");
        keyVal.set(Integer.parseInt(columns[columnIndex]));
        context.write(keyVal, value);
    }
}