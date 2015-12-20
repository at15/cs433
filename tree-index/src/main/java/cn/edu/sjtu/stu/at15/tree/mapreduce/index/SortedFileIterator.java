package cn.edu.sjtu.stu.at15.tree.mapreduce.index;

import org.mellowtech.core.bytestorable.CBInt;
import org.mellowtech.core.bytestorable.CBString;
import org.mellowtech.core.collections.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by at15 on 15-12-20.
 */
public class SortedFileIterator implements Iterator<KeyValue<CBInt, CBString>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SortedFileIterator.class);
    private BufferedReader br;
    private String cachedLine;
    private Boolean end;

    public SortedFileIterator(BufferedReader br) {
        this.br = br;
        this.end = false;
    }

    public boolean hasNext() {
//        LOGGER.info("call has next");, has next is called, but why the tree is empty?
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

    public KeyValue<CBInt, CBString> next() {
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
//        LOGGER.info(line); // line is also working
        Integer key = Integer.valueOf(splits[0]);
//        LOGGER.info("key is " + key);
        return new KeyValue<CBInt, CBString>(new CBInt(key),
                new CBString(line));
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
