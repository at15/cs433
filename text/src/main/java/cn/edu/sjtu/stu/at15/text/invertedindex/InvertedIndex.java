package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * Created by gpl on 15/11/7.
 */
public class InvertedIndex {
    private static String jobName = "invertedindex";

    public static void main(String[] args) throws Exception {
        run(args[0], args[1]);
    }

    public static void run(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(InvertedIndex.class);
        conf.setJobName(jobName);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Combine.class);
        conf.setReducerClass(Reduce.class);

        FileInputFormat.addInputPath(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));

        JobClient.runJob(conf);
    }
}
