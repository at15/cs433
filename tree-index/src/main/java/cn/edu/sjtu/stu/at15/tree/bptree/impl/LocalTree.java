package cn.edu.sjtu.stu.at15.tree.bptree.impl;

import cn.edu.sjtu.stu.at15.tree.bptree.BPlusTree;
import cn.edu.sjtu.stu.at15.tree.bptree.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by at15 on 15-12-20.
 *
 * store tree in local fs
 */
public class LocalTree<K extends Comparable, V> extends BPlusTree<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalTree.class);
    private Long size;
    private K minKey;
    private K maxKey;

    public LocalTree() {
        size = 0L;
    }

    // return value for get single value
    public V get(K key) {
        return null;
    }

    public Long size() {
        return size;
    }

    public K getMinKey() {
        return minKey;
    }

    public K getMaxKey() {
        return maxKey;
    }

    // bulk loading
    public void bulkLoading(Iterator<KeyValue<K, V>> sorted) {
        // FIXME: use tree map to store
        KeyValue<K, V> kv = sorted.next();
        minKey = kv.key;
        while (kv != null) {
            maxKey = kv.key;
            // should insert it ... the bulk loading logic here
            // bulk insert or sth...
            kv = sorted.next();
        }
    }

    public boolean save() {
        return true;
    }
}
