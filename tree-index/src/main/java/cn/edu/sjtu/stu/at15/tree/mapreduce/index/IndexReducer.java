package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by at15 on 15-12-20.
 */
public class IndexReducer extends
        Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {

        for (Text val : values) {
            context.write(key, val);
        }

    }
}
