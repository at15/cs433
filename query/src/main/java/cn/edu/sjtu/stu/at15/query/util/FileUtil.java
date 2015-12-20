package cn.edu.sjtu.stu.at15.query.util;

import java.io.File;

/**
 * Created by at15 on 15-12-20.
 */
public class FileUtil {
    public static boolean localFileExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }
}
