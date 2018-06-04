package DesignPattern.Singleton;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-04
 * Time: 17:55
 */

/**
 * DCL(Double Check Lock)
 * 必须结合volatile使用,以及需要JDK1.5及以上。
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance;
    private DCLSingleton() {}

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) instance = new DCLSingleton();
            }
        }
        return instance;
    }
}
