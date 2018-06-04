package DesignPattern.Singleton;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-04
 * Time: 17:53
 */

/**
 * Hungry Singleton: 基于classloader机制,避免了多线程同步问题(ThreadSafe)
 * 容易产生垃圾对象。
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton() {}

    public static HungrySingleton getInstance() {return instance;}
}

/**
 * 同样是利用classloader机制(保证唯一性)
 * 但结合static inner 静态内部类,实现了lazy loading。
 */
class StaticInnerSingleton {
    private StaticInnerSingleton() {}

    private static class  SingletonHolder {
        private static StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return SingletonHolder.instance;
    }
}