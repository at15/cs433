package cn.edu.sjtu.stu.at15.tree.util.generator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by at15 on 15-12-18.
 */
public class Num {
    private Integer start;
    private Integer end;

    public Num(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public int generate() {
        // http://stackoverflow.com/a/363692
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(start, end + 1);
    }
}
