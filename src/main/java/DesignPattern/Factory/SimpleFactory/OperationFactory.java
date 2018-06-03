package DesignPattern.Factory.SimpleFactory;

import DesignPattern.Factory.Operation;
import DesignPattern.Factory.OperationAdd;
import DesignPattern.Factory.OperationSub;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 19:12
 */

// 写在后面: 其实下面的写法并不是最优。
// 个人认为应该把Operation变成单例, getResult传入参数,而不是把参数作为类变量(线程安全)。

// A simple factory: to get the class u need
// along with Polymorphism: use the base class 'Operation' reference
public class OperationFactory {
    public static Operation createOperation(String operate) {
        Operation oper = null;
        switch (operate) {
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
        }
        return oper;
    }

    public static void main(String[] args) {
        Operation oper = OperationFactory.createOperation("-");
        if (oper != null) {
            oper.setNumberA(3.5);
            oper.setNumberB(1.2);
            System.out.println(oper.getResult());
        }
    }
}
