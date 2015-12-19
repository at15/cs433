package cn.edu.sjtu.stu.at15.tree.mapreduce.pre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by at15 on 15-12-19.
 */
public class PreSortMapper extends
        Mapper<LongWritable, Text, IntWritable, Text> {
    private Integer columnIndex = 0;
    private IntWritable keyVal;
    public static final String COLUMN_INDEX_CONFIG_NAME = "presort.column.index";
    public static final Logger LOGGER = LoggerFactory.getLogger(PreSortMapper.class);

    // get the column from configuration
    // TODO: check if the config is working
    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        Configuration configuration = context.getConfiguration();
        columnIndex = Integer.parseInt(configuration.get(COLUMN_INDEX_CONFIG_NAME, "0"));
        LOGGER.info("column index is " + columnIndex);
        keyVal = new IntWritable();
    }

    // output specific column as key
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] columns = line.split("\\t");
        keyVal.set(Integer.parseInt(columns[columnIndex]));
        context.write(keyVal, value);
    }
}