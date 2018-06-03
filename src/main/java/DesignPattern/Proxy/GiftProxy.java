package DesignPattern.Proxy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 21:53
 */
// proxy pattern:为对象提供一种代理以控制对这个对象的访问。

public class GiftProxy {
    // 客户端代码如下
    public static void main(String[] args) {
        SchoolGirl girl = new SchoolGirl();
        girl.setName("wt");
        Proxy proxy = new Proxy(girl);
        proxy.giveDolls();   // 实际上是proxy内部的pursuit送的,也即调用pursuit的give方法
        proxy.giveFlowers();
        proxy.giveChocolate();
    }
}


interface GiveGift {
    void giveDolls();
    void giveFlowers();
    void giveChocolate();
}

// 真正的追求者(真正送礼物的人)
class Pursuit implements GiveGift {
    SchoolGirl girl;
    public Pursuit(SchoolGirl girl) {
        this.girl = girl;
    }
    @Override
    public void giveDolls() {
        System.out.println("Give dolls to " + girl);
    }

    @Override
    public void giveFlowers() {
        System.out.println("Give flowers to " + girl);
    }

    @Override
    public void giveChocolate() {
        System.out.println("Give chocolate to " + girl);
    }
}

// 代理,传递礼物
// 和pursuit继承自同一个接口!!!
class Proxy implements GiveGift{
    Pursuit gg;
    public Proxy(SchoolGirl mm) {
        gg = new Pursuit(mm);
    }

    @Override
    public void giveDolls() {
        gg.giveDolls();   // 实际上是调用pursuit的方法!!!
    }

    @Override
    public void giveFlowers() {
        gg.giveFlowers();
    }

    @Override
    public void giveChocolate() {
        gg.giveChocolate();
    }
}

// 被追求者(被送礼物的人)
class SchoolGirl {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SchoolGirl{" +
                "name='" + name + '\'' +
                '}';
    }
}