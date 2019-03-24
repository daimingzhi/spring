package base;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dmz
 * @date Create in 21:10 2019/3/23
 */
public class StringUtilsTest {

    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    int[] arr = {1, 2, 3, 4, 5, 6};
    String s7;
    List<String> list= new ArrayList<>();

    @Before
    public void init() {
        s1 = "认认真真学java!!!";
        s2 = "";
        s3 = null;
        s4 = "    ";
        s5 = "\r\n";
        s6 = "戴明智";
        s7 = "111123456789965432181111";
        list.add(s1);
        list.add(s6);
        list.add(s7);
    }

    @Test
    public void test01() {
        System.out.println(StringUtils.isBlank(s2));
        System.out.println(StringUtils.isBlank(s3));
        System.out.println(StringUtils.isBlank(s4));
        System.out.println(StringUtils.isBlank(s5));
    }

    @Test
    public void test02() throws Exception {
        System.out.println(StringUtils.left(s1, 6));
        System.out.println(StringUtils.rightPad(StringUtils.left(s6, 1), s6.length(), "*"));
        System.out.println(StringUtils.mid(s1, 1, 2));
        System.out.println(s1.substring(0, 2));
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, 1)));
        System.out.println(StringUtils.removeStart(s1, "认认"));
        System.out.println(StringUtils.remove(s1, "学java"));
        System.out.println(StringUtils.removeEnd(s1, "!!!"));
        System.out.println(StringUtils.length(s1));
        System.out.println(StringUtils.isBlank(s5));
        System.out.println(StringUtils.strip(s7, "1"));
        System.out.println(StringUtils.stripStart(s7, "1"));
        String join = StringUtils.join("1","2","3","4",list);
        String format = String.format("Hi %s,%s", "张三", "李四");
        System.out.println(format);
        System.out.println(join);
    }
}
