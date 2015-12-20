package cn.edu.sjtu.stu.at15.tree.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 15-12-20.
 */
public class Cli {
    private static final Logger LOGGER = LoggerFactory.getLogger(Cli.class);

    public int run(String[] args) throws Exception {
        Integer paramStartIndex = args[0].equals("query") ? 1 : 0;
        return 0;
    }


}
