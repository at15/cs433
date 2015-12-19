package cn.edu.sjtu.stu.at15.tree.mapreduce;

import cn.edu.sjtu.stu.at15.tree.mapreduce.pre.PreSortDriver;
import cn.edu.sjtu.stu.at15.tree.mapreduce.sort.SortDriver;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by at15 on 15-12-19.
 */
public class Runner {
    public static void main(String[] args) throws Exception {
        if (args.length < 0) {
            System.out.println("must specify which mr job to run!");
        }
        if (args[0].equals("pre-sort")) {
            int exitCode = ToolRunner.run(new PreSortDriver(), args);
            System.exit(exitCode);
        }
        if(args[0].equals("sort")){
            int exitCode = ToolRunner.run(new SortDriver(), args);
            System.exit(exitCode);
        }
        System.out.println("unsupported job " + args[0]);
    }
}
