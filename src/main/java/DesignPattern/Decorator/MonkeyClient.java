package DesignPattern.Decorator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 21:20
 */
// Decorator:


// 例子:齐天大圣72变, 每一种变化都可以带来一种附加的本领。比如变成鱼,就可以在水里游。
public class MonkeyClient {
    public static void main(String[] args) {
        TheGreatestSage sage = new Monkey();
        sage.move();

        // 注意:这里如果写成 Bird bird = new Bird(sage) 就不合理了,因为我们它始终还是猴子,只是"猴子变的鸟儿"
        TheGreatestSage bird = new Bird(sage);   // 变身成鸟的monkey: 本质是用鸟"装饰"的monkey
        bird.move();

        new Fish(bird).move();  // 再把鱼儿的功能加到"有鸟儿功能的猴子"身上
    }
}


// 齐天大圣尊号
interface TheGreatestSage {
    public void move();
}


// 具体角色
class Monkey implements TheGreatestSage {
    @Override
    public void move() {
        System.out.println("Monkey Move");
    }
}

// Decorator 实现了齐天大圣接口
abstract class Change implements TheGreatestSage {
    private TheGreatestSage sage;
    public Change(TheGreatestSage sage) {
        this.sage = sage;
    }
}

// 具体装饰角色"鱼儿"  override move方法
class Fish extends Change {
    public Fish(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Fish Move");
    }
}

// 具体装饰角色2"鸟儿"
class Bird extends Change {
    public Bird(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Bird Move");
    }
}