package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gpl on 15/11/7.
 */
public class Map extends MapReduceBase
        implements Mapper<LongWritable, Text, Text, Text> {
    private static final Text one = new Text("1");
    private static final Text keyValue = new Text();
    private static final String SEP = ":";

    // -> <"I:a.txt","1">
    public void map(LongWritable key, Text value,
                    OutputCollector<Text, Text> output,
                    Reporter reporter) throws IOException {
        String line = value.toString();
        StringTokenizer itr = new StringTokenizer(line);
        FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
        String fileName = fileSplit.getPath().getName();

        while (itr.hasMoreTokens()) {
            keyValue.set(itr.nextToken() + SEP + fileName);
            output.collect(keyValue, one);
        }
    }
}
