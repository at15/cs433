package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by at15 on 15-12-20.
 * <p/>
 * just distribute the partition files to reducers
 */
public class IndexMapper extends
        Mapper<LongWritable, Text, Text, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexMapper.class);

    // generate the partition file path from meta
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        // need to split the text value
        NumberFormat nf = new DecimalFormat("00000");
        String line = value.toString();
        String[] columns = line.split("\\t");
        Integer partitionId = Integer.parseInt(columns[0]);
        String partitionFile = PathConstant.SORT_OUTPUT + "/" + "part-r-" + nf.format(partitionId);
//        LOGGER.info("partition file is " + partitionFile);
        context.write(new Text(partitionFile), value);
    }
}