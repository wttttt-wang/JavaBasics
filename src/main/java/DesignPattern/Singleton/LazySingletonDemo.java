package DesignPattern.Singleton;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-04
 * Time: 17:49
 */

/**
 * Lazy singleton: lazy loading
 * !!! Not thread-safe !!!
 */
public class LazySingletonDemo {
    private static LazySingletonDemo instance;
    private LazySingletonDemo() {}

    public static LazySingletonDemo getInstance() {
        if (instance == null) instance = new LazySingletonDemo();
        return instance;
    }
}


// Lazy singleton2: Thread safe
// !!! Low efficiency !!!
class LazySingletonThreadSafe {
    private static LazySingletonThreadSafe instance;
    private LazySingletonThreadSafe() {}

    public static synchronized LazySingletonThreadSafe getInstance() {
        if (instance == null) instance = new LazySingletonThreadSafe();
        return instance;
    }
}