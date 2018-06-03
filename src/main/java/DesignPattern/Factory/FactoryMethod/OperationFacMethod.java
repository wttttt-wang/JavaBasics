package DesignPattern.Factory.FactoryMethod;

import DesignPattern.Factory.Operation;
import DesignPattern.Factory.OperationAdd;
import DesignPattern.Factory.OperationSub;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-03
 * Time: 10:55
 */
public class OperationFacMethod {
    // client
    public static void main(String[] args) {
        OperFactory addFac = new OperationAddFac();  // 对比之下,simple factory是传"+"给工厂,然后工厂返回相应的operation。
        Operation operation = addFac.createOpertion();
        operation.setNumberA(1.0);
        operation.setNumberB(2.1);
        System.out.println(operation.getResult());
    }
}

// 抽象工厂
interface OperFactory {
    Operation createOpertion();
}

// 对具体operation各建一个具体工厂(implements OperFactory)
class OperationAddFac implements OperFactory{
    @Override
    public Operation createOpertion() {
        return new OperationAdd();
    }
}

class OperationSubFac implements OperFactory {
    @Override
    public Operation createOpertion() {
        return new OperationSub();
    }
}
