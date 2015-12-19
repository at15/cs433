package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by at15 on 15-12-20.
 * <p/>
 * just distribute the partition files to reducers
 */
public class IndexMapper extends
        Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        // TODO: I could read the file here, and write every line as k-v, but the sort
        // can no be ensure I guess?
        context.write(value, value);
    }
}