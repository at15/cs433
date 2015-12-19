package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * Created by at15 on 15-12-19.
 *
 * https://github.com/apache/hadoop/blob/trunk/hadoop-mapreduce-project/hadoop-mapreduce-client/hadoop-mapreduce-client-core/src/main/java/org/apache/hadoop/mapreduce/lib/input/KeyValueTextInputFormat.java
 *
 */
public class IntegerKeyFileInputFormat extends FileInputFormat<IntWritable, Text> {
    @Override
    protected boolean isSplitable(JobContext context, Path file) {
        final CompressionCodec codec =
                new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
        if (null == codec) {
            return true;
        }
        return codec instanceof SplittableCompressionCodec;
    }

    public RecordReader<IntWritable, Text> createRecordReader(InputSplit genericSplit,
                                                       TaskAttemptContext context) throws IOException {

        context.setStatus(genericSplit.toString());
        return new IntegerKeyRecordReader(context.getConfiguration());
    }
}
