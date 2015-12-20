package cn.edu.sjtu.stu.at15.tree.bptree;

/**
 * Created by at15 on 15-12-20.
 *
 * @deprecated
 */
public class KeyValue<K extends Comparable, V> {
    public K key;
    public V value;

    public KeyValue(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public String toString() {
        return "k: " + key + " v: " + value;
    }
}
