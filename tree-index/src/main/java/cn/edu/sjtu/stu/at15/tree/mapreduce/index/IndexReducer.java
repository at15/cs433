package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.mapreduce.PathConstant;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import org.mellowtech.core.bytestorable.CBInt;
import org.mellowtech.core.bytestorable.CBString;
import org.mellowtech.core.collections.tree.BTree;
import org.mellowtech.core.collections.tree.BTreeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by at15 on 15-12-20.
 */
public class IndexReducer extends
        Reducer<Text, Text, Text, Text> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexReducer.class);

    public void reduce(Text key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {
        // create local index folder
        File localIndexFolder = new File(PathConstant.LOCAL_INDEX_FOLDER);
        // TODO: check if dir is created
        localIndexFolder.mkdir();

        LOGGER.info("start reading file " + key.toString());
        Path partitionPath = new Path("hdfs://" + key.toString());
        FileSystem fs = FileSystem.get(context.getConfiguration());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(partitionPath)));
        SortedFileIterator iterator = new SortedFileIterator(br);

        String indexFileName = new String(Base64.encodeBase64(key.getBytes()));
        String indexFilePathPrefix = PathConstant.LOCAL_INDEX_FOLDER + "/" + indexFileName;
        String indexFilePath = PathConstant.LOCAL_INDEX_FOLDER + "/" + indexFileName + ".idx";
        BTree bt = new BTreeBuilder().valuesInMemory(true)
                .build(new CBInt(), new CBString(), indexFilePathPrefix);
        bt.createIndex(iterator);
        bt.save();
        bt.close();
        // TODO: write meta data as well, the mapper may need more data
        // TODO: have a meta class
        LOGGER.info("uploading index file to HDFS");
        // TODO: should have .val file, why I only got .idx file
        Path idxFile = new Path(indexFilePath);
        Path idxFileHDFS = new Path("/tmp/" + indexFileName + ".idx");
//        Path idxFileHDFS = new Path("hdfs:///tmp/" + indexFileName); // ? need hdfs
        fs.copyFromLocalFile(false, true, idxFile, idxFileHDFS);
        LOGGER.info("upload completed");

        context.write(key, new Text(indexFileName));

    }
}
