package DesignPattern.Factory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-03
 * Time: 10:58
 */

public class OperationSub extends Operation {
    @Override
    public double getResult() {
        return numberA - numberB;
    }
}