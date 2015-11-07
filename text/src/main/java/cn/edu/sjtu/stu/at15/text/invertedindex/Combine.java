package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by gpl on 15/11/7.
 */
public class Combine extends Reducer<Text, Text, Text, Text> {
    private static final String SEP = ":";

    // take <"I:a.txt","1"> <"I:a.txt","1"> -> <"I","a.txt:2">
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException

    {
        int sum = 0;
        for (Text text : values) {
            sum += Integer.parseInt(text.toString());
        }
        String[] wordAndName = key.toString().split(SEP);
//        throw new IOException("debug key is " + wordAndName[0]);
        context.write(new Text(wordAndName[0]), new Text(wordAndName[1] + SEP + sum));
//        context.write(new Text("aaaaa"), new Text(wordAndName[1] + SEP + sum));
    }
}
