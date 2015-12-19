package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

/**
 * Created by at15 on 15-12-19.
 * <p/>
 * https://github.com/apache/hadoop/blob/trunk/hadoop-mapreduce-project/hadoop-mapreduce-client/hadoop-mapreduce-client-core/src/main/java/org/apache/hadoop/mapreduce/lib/input/KeyValueLineRecordReader.java
 */
public class IntegerKeyRecordReader extends RecordReader<IntWritable, Text> {
    public static final String KEY_VALUE_SEPERATOR =
            "mapreduce.input.keyvaluelinerecordreader.key.value.separator";

    private final LineRecordReader lineRecordReader;

    private byte separator = (byte) '\t';

    private Text innerValue;

    // a hack to change the first word to a int
    private IntWritable key;
    private Text tempKey;

    private Text value;

    public Class getKeyClass() {
        return IntWritable.class;
    }

    public IntegerKeyRecordReader(Configuration conf)
            throws IOException {

        lineRecordReader = new LineRecordReader();
        String sepStr = conf.get(KEY_VALUE_SEPERATOR, "\t");
        this.separator = (byte) sepStr.charAt(0);
        tempKey = new Text();
    }

    public void initialize(InputSplit genericSplit,
                           TaskAttemptContext context) throws IOException {
        lineRecordReader.initialize(genericSplit, context);
    }

    public static int findSeparator(byte[] utf, int start, int length,
                                    byte sep) {
        for (int i = start; i < (start + length); i++) {
            if (utf[i] == sep) {
                return i;
            }
        }
        return -1;
    }

    public void setKeyValue(IntWritable key, Text value, byte[] line,
                                   int lineLen, int pos) {
        if (pos == -1) {
            tempKey.set(line, 0, lineLen);
            key.set(Integer.parseInt(tempKey.toString()));
            value.set("");
        } else {
            tempKey.set(line, 0, pos);
            key.set(Integer.parseInt(tempKey.toString()));
            value.set(line, pos + 1, lineLen - pos - 1);
        }
    }

    /**
     * Read key/value pair in a line.
     */
    public synchronized boolean nextKeyValue()
            throws IOException {
        byte[] line = null;
        int lineLen = -1;
        if (lineRecordReader.nextKeyValue()) {
            innerValue = lineRecordReader.getCurrentValue();
            line = innerValue.getBytes();
            lineLen = innerValue.getLength();
        } else {
            return false;
        }
        if (line == null)
            return false;
        if (key == null) {
            key = new IntWritable();
        }
        if (value == null) {
            value = new Text();
        }
        int pos = findSeparator(line, 0, lineLen, this.separator);
        setKeyValue(key, value, line, lineLen, pos);
        return true;
    }

    public IntWritable getCurrentKey() {
        return key;
    }

    public Text getCurrentValue() {
        return value;
    }

    public float getProgress() throws IOException {
        return lineRecordReader.getProgress();
    }

    public synchronized void close() throws IOException {
        lineRecordReader.close();
    }
}
