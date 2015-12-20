package cn.edu.sjtu.stu.at15.query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by at15 on 15-12-20.
 */
public class HDFSClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(HDFSClient.class);
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

    public Configuration getConf() {
        return conf;
    }

    public FileSystem getHdfs() {
        return hdfs;
    }

    public void copyToLocal(String remote, String local) throws IOException {
        LOGGER.info("start copy " + remote + " to " + local);
        hdfs.copyToLocalFile(false, new Path(remote), new Path(local));
        LOGGER.info("end copy " + remote + " to " + local);
    }
}
