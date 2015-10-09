package cn.edu.sjtu.stu.at15.video;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

/**
 * Created by at15 on 10/9/2015.
 */
public class HDFSClient {
    public static void main(String[] args) throws Exception {
        Path p = new Path("/usr/media/t.mp4");
        File file = new File("video/t.mp4");

        FileInputStream is = new FileInputStream(file);
        BufferedInputStream bi = new BufferedInputStream(is);

        Configuration conf= new Configuration();
//        conf.addResource(new Path("etc/core-site.xml"));
//        conf.addResource(new Path("etc/hdfs-site.xml"));
//        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.33.10:50070"),conf);
        FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"),conf);

        FSDataOutputStream fo = fs.create(p);
        byte[] buffer = new byte[1024];
        int index;
        while (-1 != (index = bi.read(buffer, 0, buffer.length))) {
            fo.write(buffer, 0, index);
        }
        fo.close();
        bi.close();
        is.close();
    }
}
