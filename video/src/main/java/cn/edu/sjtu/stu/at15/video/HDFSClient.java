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
import java.net.URISyntaxException;

/**
 * Created by at15 on 10/9/2015.
 */
public class HDFSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HDFSClient.class);
    protected Configuration conf;
    protected FileSystem hdfs;

    public HDFSClient() throws IOException {
        conf = new Configuration();
        try {
            hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
        } catch (URISyntaxException ex) {
            throw new IOException(ex);
        }
    }

    public void upload(String src, String dst) throws Exception {
        // handle the input
        // TODO: check file and catch
        File srcFile = new File(src);
        upload(srcFile, dst);
    }

    public void upload(File srcFile, String dst) throws IOException {
        LOGGER.info("start uploading " + srcFile.getName() + " to " + dst);
        FileInputStream inputStream = new FileInputStream(srcFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        Path dstPath = new Path(dst);
        FSDataOutputStream writer = hdfs.create(dstPath);
        byte[] buffer = new byte[1024];
        int index;


        while (-1 != (index = bufferedInputStream.read(buffer, 0, buffer.length))) {
            writer.write(buffer, 0, index);
        }
        writer.close();
        inputStream.close();
        bufferedInputStream.close();

        LOGGER.info("finish uploading " + srcFile.getName() + " to " + dst);
    }
}
