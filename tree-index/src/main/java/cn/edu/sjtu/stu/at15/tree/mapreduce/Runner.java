package cn.edu.sjtu.stu.at15.tree.mapreduce;

import cn.edu.sjtu.stu.at15.tree.mapreduce.index.IndexDriver;
import cn.edu.sjtu.stu.at15.tree.mapreduce.meta.MetaDriver;
import cn.edu.sjtu.stu.at15.tree.mapreduce.pre.PreSortDriver;
import cn.edu.sjtu.stu.at15.tree.mapreduce.sort.SortDriver;
import cn.edu.sjtu.stu.at15.tree.query.Cli;
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
        if (args[0].equals("sort")) {
            int exitCode = ToolRunner.run(new SortDriver(), args);
            System.exit(exitCode);
        }
        if (args[0].equals("index")) {
            int exitCode = ToolRunner.run(new IndexDriver(), args);
            System.exit(exitCode);
        }
        if (args[0].equals("meta")) {
            int exitCode = ToolRunner.run(new MetaDriver(), args);
            System.exit(exitCode);
        }
        if (args[0].equals("query")) {
            int exitCode = new Cli().run(args);
            System.exit(exitCode);
        }
        System.out.println("unsupported job or action" + args[0]);
    }
}
