package SyntacticSugar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 11:32
 */
public class GenericsTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();   // Map map = new HashMap(); 类型擦除
        map.put("a", "mapa");
        String h = map.get("a");   // String h = (String)map.get("a");  强制转换

        System.out.println(new ArrayList<String>().getClass() == new ArrayList<Integer>().getClass());
    }
}
