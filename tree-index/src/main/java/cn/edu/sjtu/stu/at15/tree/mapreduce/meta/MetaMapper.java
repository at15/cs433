package cn.edu.sjtu.stu.at15.tree.mapreduce.meta;

import cn.edu.sjtu.stu.at15.tree.mapreduce.MetaRow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by at15 on 15-12-20.
 * <p/>
 * merge all the sub-index meta into one file
 */
public class MetaMapper extends
        Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        MetaRow meta = new MetaRow(line);
        context.write(new Text(meta.getPartitionIdAsString()), new Text(meta.withOutPartitionId()));
    }
}
