package cn.zhou.demo.utils;

public class MyUtil {
    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static Boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static Boolean isNull(String str) {
        if (str == null || (str.equals("") | str.length() < 0)) {
            return true;
        }
        return false;
    }

    public static Boolean isNotNull(String str) {
        return !isNull(str);
    }


    public static Boolean isAllNull(String... str) {
        for (String s : str) {
            if (isNotNull(s)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isAllNotNull(String... str) {
        return !isAllNull(str);
    }


}
