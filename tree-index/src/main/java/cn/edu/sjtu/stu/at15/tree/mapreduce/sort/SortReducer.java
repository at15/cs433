package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import cn.edu.sjtu.stu.at15.tree.mapreduce.MetaRow;
import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
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
    private Long count;
    private MultipleOutputs<IntWritable, Text> mos;

    public void setup(Context context) {
        count = 0L;
        mos = new MultipleOutputs<IntWritable, Text>(context);
    }


    // meta is
    // partitionId | start | end | count
    public void cleanup(Context context) throws IOException, InterruptedException {
        Integer partitionId = context.getTaskAttemptID().getTaskID().getId();
        LOGGER.info("min key " + minKey);
        LOGGER.info("max key " + maxKey);
        LOGGER.info("partition id is " + partitionId);
        // FIXME: should use path from config instead of using constant
        String metaFileName = PathConstant.SORT_META_OUTPUT + "/" + partitionId;
        MetaRow meta = new MetaRow();
        meta.setPartitionId(partitionId);
        meta.setStart(minKey);
        meta.setEnd(maxKey);
        meta.setCount(count);
        mos.write("meta", new IntWritable(meta.getPartitionId()), new Text(meta.toString()),
                metaFileName);
        mos.close();
    }

    public void reduce(IntWritable key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // NOTE: the count is key count, not value count, it should be split
        count++;
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
