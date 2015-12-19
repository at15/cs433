package cn.edu.sjtu.stu.at15.tree.bptree;

/**
 * Created by at15 on 15-12-20.
 */
public class KeyValue<K extends Comparable<K>, V> {
    public K key;
    public V vaule;

    public KeyValue(K k, V v) {
        this.key = k;
        this.vaule = v;
    }
}
