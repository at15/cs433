package cn.edu.sjtu.stu.at15.tree.bptree;

import java.util.Iterator;

/**
 * Created by at15 on 15-12-20.
 *
 * @deprecated
 */
public abstract class BPlusTree<K extends Comparable, V> {

    // return value for get single value
    public abstract V get(K key);

    public abstract K getMinKey();

    public abstract K getMaxKey();

    public abstract Long size();

    // bulk loading
    public abstract void bulkLoading(Iterator<KeyValue<K, V>> sorted);

    // save
    public abstract boolean save();
}
//public abstract class BPlusTree<K extends Comparable<K>, V> {
//    // return value for get single value
//    abstract V get(K key);
//
//    // bulk loading
//    abstract void bulkLoading(Collection<KeyValue<K, V>> sorted);
//}
