package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import cn.edu.sjtu.stu.at15.tree.bptree.KeyValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by at15 on 15-12-20.
 */
public class PartitionFileIterator implements Iterator<KeyValue<Integer, String>> {
    private BufferedReader br;
    private String cachedLine;
    private Boolean end;

    public PartitionFileIterator(BufferedReader br) {
        this.br = br;
        this.end = false;
    }

    public boolean hasNext() {
        if (end) {
            return false;
        }
        try {
            cachedLine = br.readLine();
            if (cachedLine != null) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ignore) {
            end = true;
            return false;
        }
    }

    public KeyValue<Integer, String> next() {
        // TODO: how to handle no more next?
        String line;
        if (cachedLine != null) {
            line = cachedLine;
            cachedLine = null;
        } else {
            try {
                line = br.readLine();
                if (line == null) {
                    end = true;
                    return null;
                }
            } catch (IOException ignore) {
                end = true;
                return null;
            }
        }
        // parse the line and return value
        String[] splits = line.split("\\t");
        return new KeyValue<Integer, String>(Integer.valueOf(splits[0]), line);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
