package cn.edu.sjtu.stu.at15.text.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * Created by gpl on 15/11/6.
 */
public class WordCount {
    private static String jobName = "wordcount";

    public static void main(String[] args) throws Exception {
        run(args[0], args[1]);
    }

    public static void run(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(WordCount.class);
        conf.setJobName(jobName);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(WordCountMapper.class);
        conf.setReducerClass(WordCountReducer.class);

        FileInputFormat.addInputPath(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));

        JobClient.runJob(conf);
    }
}
