package cn.edu.sjtu.stu.at15.tree.mapreduce;

/**
 * Created by at15 on 15-12-19.
 */
public class PathConstant {
    public static final String PRE_SORT_INPUT = "/user/at15/tree-index/input";
    // this is also sort input
    public static final String PRE_SORT_OUTPUT = "/user/at15/tree-index/out-pre-sort";
    public static final String SORT_OUTPUT = "/user/at15/tree-index/out-sort";
    public static final String SORT_PARTITION_FILE = "/user/at15/tree-index/part.lst";
    public static final String SORT_META_OUTPUT = "/user/at15/tree-index/out-sort-meta";
    public static final String INDEX_OUTPUT = "/user/at15/tree-index/out-index";
    public static final String META_OUTPUT = "/user/at15/tree-index/out-meta";
    public static final String LOCAL_INDEX_FOLDER = "/tmp/tree-index";
    // TODO: may need to create folder
    public static final String REMOTE_INDEX_FOLDER = "/user/at15/tree-index/built-index";
}
