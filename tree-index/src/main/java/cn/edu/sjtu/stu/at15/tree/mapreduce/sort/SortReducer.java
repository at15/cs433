package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by at15 on 15-12-20.
 * <p/>
 * merge all the duplicate keys
 */
public class SortReducer extends
        Reducer<IntWritable, Text, IntWritable, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SortReducer.class);
    private Integer minKey;
    private Integer maxKey;
    private MultipleOutputs<IntWritable, Text> mos;
    private final IntWritable one = new IntWritable(1);

    public void setup(Context context) {
        mos = new MultipleOutputs<IntWritable, Text>(context);
    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        LOGGER.info("min key " + minKey);
        LOGGER.info("max key " + maxKey);
        LOGGER.info("partition id is " + context.getTaskAttemptID().getTaskID().getId());
        String metaFileName = "/tmp/meta-" + context.getTaskAttemptID().getTaskID().getId();
        mos.write("meta", one, new Text(minKey + "\t" + maxKey), metaFileName);
        mos.close();
    }

    public void reduce(IntWritable key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        if (minKey == null || minKey > key.get()) {
            minKey = key.get();
        }
        if (maxKey == null || maxKey < key.get()) {
            maxKey = key.get();
        }
        // TODO: compress the values with the same key
        for (Text v : values) {
            context.write(key, v);
        }
    }
}
