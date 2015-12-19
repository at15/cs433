package cn.edu.sjtu.stu.at15.tree.bptree.impl;

import cn.edu.sjtu.stu.at15.tree.bptree.BPlusTree;
import cn.edu.sjtu.stu.at15.tree.bptree.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by at15 on 15-12-20.
 */
public class DummyTree<K extends Comparable, V> extends BPlusTree<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyTree.class);

    // return value for get single value
    public V get(K key) {
        return null;
    }

    // bulk loading
    public void bulkLoading(Iterator<KeyValue<K, V>> sorted) {

    }

    public boolean save(){
        return true;
    }
}
