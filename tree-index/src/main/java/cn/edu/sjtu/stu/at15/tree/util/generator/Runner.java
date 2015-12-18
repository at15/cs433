package cn.edu.sjtu.stu.at15.tree.util.generator;

/**
 * Created by at15 on 15-12-18.
 */
public class Runner {
    // TODO： generate fake data in multi thread and write to file
    public static void main(String[] args) throws Exception{
        Num num = new Num(0,100);
        System.out.println(num.generate());
        System.out.println(num.generate());
    }
}
