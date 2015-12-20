package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.bptree.BPlusTree;
import cn.edu.sjtu.stu.at15.tree.bptree.impl.DummyTree;
import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.BufferedReader;
import java.io.File;
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
        // create local index folder
        File localIndexFolder = new File(PathConstant.LOCAL_INDEX_FOLDER);
        // TODO: check if dir is created
        localIndexFolder.mkdir();

        Path partitionPath = new Path("hdfs://" + key.toString());
        FileSystem fs = FileSystem.get(context.getConfiguration());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(partitionPath)));

        String indexFileName = PathConstant.LOCAL_INDEX_FOLDER + "/" + new String(Base64.encodeBase64(key.getBytes()));


        BPlusTree<Integer, String> bPlusTree = new DummyTree<Integer, String>();
        bPlusTree.bulkLoading(new PartitionFileIterator(br));
        bPlusTree.save();
        context.write(key, new Text(bPlusTree.getMinKey() +
                "\t" + bPlusTree.getMaxKey() +
                "\t" + bPlusTree.size() +
                "\t" + indexFileName));

    }
}
