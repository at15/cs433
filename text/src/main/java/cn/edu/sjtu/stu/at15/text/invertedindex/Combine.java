package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by gpl on 15/11/7.
 */
public class Combine extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    private static final String SEP = ":";

    // take <"I:a.txt","1"> <"I:a.txt","1"> -> <"I","a.txt:2">
    public void reduce(Text key, Iterator<Text> values,
                       OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException

    {
        int sum = 0;
        while (values.hasNext()) {
            sum += 1;
        }
        String[] wordAndName = key.toString().split(SEP);
        output.collect(new Text(wordAndName[0]), new Text(wordAndName[1] + SEP + String.valueOf(sum)));
    }
}
