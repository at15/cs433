package cn.edu.sjtu.stu.at15.text.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by gpl on 15/11/6.
 *
 * @TODO
 */
public class WordCountMapper extends MapReduceBase
//        implements Mapper<LongWritable, Text, Text, IntWritable> {
        implements Mapper<LongWritable, Text, Text, Text> {
    private static final IntWritable one = new IntWritable(1);
    private static final Text word = new Text();
    private static final Text location = new Text();

    // key is the offset in input text
    // reporter got some meta info for input
//    public void map(LongWritable key, Text value,
////                    OutputCollector<Text, IntWritable> output,
//                    OutputCollector<Text, Text> output,
//                    Reporter reporter) throws IOException {
//        String line = value.toString();
//        StringTokenizer itr = new StringTokenizer(line);
//        FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
//        String fileName = fileSplit.getPath().getName();
//        location.set(fileName);
//
//        while (itr.hasMoreTokens()) {
//            word.set(itr.nextToken());
////            output.collect(word, one);
//            output.collect(word, location);
//        }
//    }

    public void map(LongWritable key, Text val,
                    OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException {

        FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
        String fileName = fileSplit.getPath().getName();
        location.set(fileName);

        String line = val.toString();
        StringTokenizer itr = new StringTokenizer(line.toLowerCase());
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            output.collect(word, location);
        }
    }
}
