package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gpl on 15/11/7.
 */
public class Map extends Mapper<LongWritable, Text, Text, Text> {
    private static final Text one = new Text("1");
    private static final Text keyValue = new Text();
    private static final String SEP = ":";

    // -> <"I:a.txt","1">
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer itr = new StringTokenizer(line);
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String fileName = fileSplit.getPath().getName();

        while (itr.hasMoreTokens()) {
            keyValue.set(itr.nextToken() + SEP + fileName);
            context.write(keyValue, one);
        }
    }
}
