package cn.edu.sjtu.stu.at15.tree.mapreduce.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.partition.*;
import org.apache.hadoop.mapreduce.lib.reduce.*;
import org.apache.hadoop.util.*;

/**
 * Created by at15 on 15-12-19.
 * <p/>
 * sort input data by certain column
 */
public class SortDriver extends Configured implements Tool {
    public static final Logger LOGGER = LoggerFactory.getLogger(SortDriver.class);

    public int run(String[] args) throws Exception {
        int numReducers = 5;
        Path input = new Path("/user/at15/tree-index/input");
        Path partitionFile = new Path("/user/at15/tree-index/part.lst");
        Path output = new Path("/user/at15/tree-index/output");
        return 0;
    }
}
