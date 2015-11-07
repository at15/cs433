package cn.edu.sjtu.stu.at15.text.invertedindex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Created by gpl on 15/11/7.
 */
public class Reduce extends MapReduceBase
        implements Reducer<Text, Text, Text, Text> {
    private static final String SEP = ":";
    // FIXME dumb ass naming ....
    private static final String SEP2 = ",";

    // take <"I","a.txt:1">, <"I","a.txt:1">, <"I","b.txt:2"> -> <"I","a.txt:2,b.txt:2">
    public void reduce(Text key, Iterator<Text> values,
                       OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException

    {
        // use a hash map to keep file names
        Map<String, Integer> files = new HashMap<String, Integer>();
        String[] fileAndCount;
        String file;
        Integer count;
        while (values.hasNext()) {
            fileAndCount = values.next().toString().split(SEP);
            file = fileAndCount[0];
            count = Integer.valueOf(fileAndCount[1]);
            if (!files.containsKey(file)) {
                files.put(file, 0);
            }
            files.put(file, files.get(file) + count);
        }
        // generate the very long value
        StringBuilder sb = new StringBuilder();
        Iterator it = files.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            sb.append(pair.getKey() + SEP + pair.getValue() + SEP2);
        }
        // TODO: trim the last ,
        output.collect(key, new Text(sb.toString()));
    }
}
