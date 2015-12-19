package cn.edu.sjtu.stu.at15.tree.bptree;

import java.util.Collection;

/**
 * Created by at15 on 15-12-20.
 */
public abstract class BPlusTree<K extends Comparable<K>, V> {
    // return value for get single value
    abstract V get(K key);

    // bulk loading
    abstract void bulkLoading(Collection<KeyValue<K, V>> sorted);
}
