package DesignPattern.TemplateMethod;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-03
 * Time: 11:19
 */
// 模板方法:本质是把不变行为搬移到超类,去除子类中的重复代码。
// 对于一些场景:由一系列步骤组成的过程,这个过程从高层次看是一样的,只是有些步骤的实现不同。
public class TemplateMethodTemplate {
    // client
    public static void main(String[] args) {
        AbstractTemplate templateA = new ConcreteClassA();
        templateA.templateMethod();

        new ConcreteClassB().templateMethod();
    }
}

// 抽象方法:定义顶级逻辑的骨架,而逻辑的组成步骤在相应的抽象操作中,从而推迟到子类实现。
abstract class AbstractTemplate {
    abstract void primitiveOpertion1();
    abstract void primitiveOpertion2();

    void templateMethod() {
        primitiveOpertion1();
        primitiveOpertion2();
    }
}

// 具体组成步骤的实现
class ConcreteClassA extends AbstractTemplate {
    @Override
    void primitiveOpertion1() {
        System.out.println("具体类A方法1实现");
    }

    @Override
    void primitiveOpertion2() {
        System.out.println("具体类A方法2实现");
    }
}

class ConcreteClassB extends AbstractTemplate {
    @Override
    void primitiveOpertion1() {
        System.out.println("具体类B方法1实现");
    }

    @Override
    void primitiveOpertion2() {
        System.out.println("具体类B方法2实现");
    }
}


