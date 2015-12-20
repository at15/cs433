package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.bptree.BPlusTree;
import cn.edu.sjtu.stu.at15.tree.bptree.impl.DummyTree;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by at15 on 15-12-20.
 */
public class IndexReducer extends
        Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // try if we can read a file, in fact, values should only have
        // one value, which is the partition
        for (Text val : values) {
            Path partitionPath = new Path("hdfs://" + val.toString());
            FileSystem fs = FileSystem.get(context.getConfiguration());
            BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(partitionPath)));
//
//            // line count
//            Integer lineCount = 0;
//            try {
//                String line;
//                line=br.readLine();
//                while (line != null){
//                    lineCount++;
//                    // be sure to read the next line otherwise you'll get an infinite loop
//                    line = br.readLine();
//                }
//            } finally {
//                // you should close out the BufferedReader
//                br.close();
//            }
//            context.write(key, new Text(String.valueOf(lineCount)));

            BPlusTree<Integer, String> bPlusTree = new DummyTree<Integer, String>();
            bPlusTree.bulkLoading(new PartitionFileIterator(br));
            bPlusTree.save();
            context.write(key, new Text(bPlusTree.getMinKey() + "\t" + bPlusTree.getMaxKey()));
        }

    }
}
