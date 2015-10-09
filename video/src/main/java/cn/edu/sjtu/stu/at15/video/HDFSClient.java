package cn.edu.sjtu.stu.at15.video;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;

/**
 * Created by at15 on 10/9/2015.
 */
public class HDFSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HDFSClient.class);
    
    public void upload(String src, String dst) throws Exception {
        // TODO: check file and catch
        File srcFile = new File(src);
        FileInputStream inputStream = new FileInputStream(srcFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        Configuration conf = new Configuration();
        FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
        Path dstPath = new Path(dst);

        FSDataOutputStream writer = hdfs.create(dstPath);
        byte[] buffer = new byte[1024];
        int index;
        LOGGER.info("start uploading " + src + " to " + dst);
        while (-1 != (index = bufferedInputStream.read(buffer, 0, buffer.length))) {
            writer.write(buffer, 0, index);
        }
        writer.close();
        inputStream.close();
        bufferedInputStream.close();

        LOGGER.info("finish uploading " + src + " to " + dst);
    }
}
