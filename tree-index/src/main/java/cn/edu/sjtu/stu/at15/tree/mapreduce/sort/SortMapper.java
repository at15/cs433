package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by at15 on 15-12-19.
 * <p/>
 * change specific column to key, ie: the second column
 */
public class SortMapper extends
        Mapper<IntWritable, Text, IntWritable, Text> {
    @Override
    public void map(IntWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] columns = line.split("\\t");
        if(columns.length != 0){
            context.write(new IntWritable(Integer.valueOf(columns[0])),
                    value);
        }
//        for (String column : line.split("\\t")) {
//            if (column.length() == 0) {
//                continue;
//            }
//            context.write(new IntWritable(Integer.valueOf(column)));
//        }
    }
}