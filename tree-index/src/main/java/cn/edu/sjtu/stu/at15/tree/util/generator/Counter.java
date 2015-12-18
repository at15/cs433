package cn.edu.sjtu.stu.at15.tree.util.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by at15 on 15-12-18.
 */
public class Counter {
    private AtomicInteger counter;

    public int getNextUniqueIndex() {
        return counter.getAndIncrement();
    }
}
