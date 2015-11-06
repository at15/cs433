package cn.edu.sjtu.stu.at15.text.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

/**
 * Created by gpl on 15/11/6.
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        // TODO: The api in YDN and official is different.
//        job.setMapperClass(WordCountMapper.class);
    }
}
