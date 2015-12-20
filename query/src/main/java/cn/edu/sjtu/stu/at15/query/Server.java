package cn.edu.sjtu.stu.at15.query;

import cn.edu.sjtu.stu.at15.query.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-12-20.
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
//        LOGGER.debug("local file exist? " + FileUtil.localFileExists(".gitignore2"));
        HDFSClient client = new HDFSClient();
        client.copyToLocal("/user/at15/tree-index/out-meta/part-r-00000","meta.txt");

    }
}
