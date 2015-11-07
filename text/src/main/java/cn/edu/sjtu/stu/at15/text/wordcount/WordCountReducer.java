package cn.edu.sjtu.stu.at15.text.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by gpl on 15/11/6.
 */
public class WordCountReducer extends MapReduceBase
//        implements Reducer<Text, IntWritable, Text, IntWritable> {
        implements Reducer<Text, Text, Text, Text> {

    //    public void reduce(Text key, Iterator<IntWritable> values,
//                       OutputCollector<Text, IntWritable> output,
//                       Reporter reporter) throws IOException {
//        int sum = 0;
//        while (values.hasNext()) {
//            sum += values.next().get();
//        }
//        output.collect(key, new IntWritable(sum));
//    }
    public void reduce(Text key, Iterator<Text> values,
                       OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException {

        boolean first = true;
        StringBuilder toReturn = new StringBuilder();
        while (values.hasNext()) {
            if (!first)
                toReturn.append(", ");
            first = false;
            toReturn.append(values.next().toString());
        }

        output.collect(key, new Text(toReturn.toString()));
    }
}
