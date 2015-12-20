package cn.edu.sjtu.stu.at15.query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by at15 on 15-12-20.
 * <p/>
 * download file from hdfs
 */
public class HDFS {
    protected Configuration conf;
    protected FileSystem hdfs;

    public HDFS() throws IOException {
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
        hdfs.copyToLocalFile(false, new Path(remote), new Path(local));
    }

}
