package DesignPattern.Strategy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 20:32
 */

// strategyContext: 持有一个strategy的引用
public class StrategyContext {
    private StrategyBase strategy;

    public StrategyContext(StrategyBase strategy) {
        this.strategy = strategy;
    }

    // 策略方法
    public void contextInterface() {
        strategy.strategyInterface();
    }
}

// 客户端代码
class Client {
    public static void main(String[] args) {
        // 由客户端决定具体使用何种策略
        // 这里缺点也很明显:客户端必须必须知道所有的策略类,并自行决定使用哪一个策略类
        // --> 一种优化方式是结合"简单工厂模式"使用: StrategyBase只需要传入字符串,而不用不用传入具体的类
        StrategyBase strategy = new ConcreteStrategyA();
        StrategyContext sc = new StrategyContext(strategy);
        sc.contextInterface();   // call strategy method...
    }

}

interface StrategyBase {
    public void strategyInterface();
}

class ConcreteStrategyA implements StrategyBase{
    @Override
    public void strategyInterface() {

    }
}

class ConcreteStrategyB implements StrategyBase {
    @Override
    public void strategyInterface() {

    }
}
