package com.zty.onlineedu.common.base.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/1/18 21:47
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public StringUtils() {
    }

    public static Object nvl(Object value, Object defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static boolean isEmpty(Collection coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Object objects[]) {
        return isNull(((Object) (objects))) || objects.length == 0;
    }

    public static boolean isNotEmpty(Object objects[]) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(Map map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    public static String trim(String str) {
        return str != null ? str.trim() : "";
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return "";
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return "";
        } else {
            return str.substring(start);
        }
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return "";
        }
        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start > end) {
            return "";
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean preCharIsUpperCase = true;
        boolean curreCharIsUpperCase = true;
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }
            curreCharIsUpperCase = Character.isUpperCase(c);
            if (i < str.length() - 1) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append('_');
            } else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    public static boolean inStringIgnoreCase(String str, String strs[]) {
        if (str != null && strs != null) {
            String as[] = strs;
            int i = as.length;
            for (int j = 0; j < i; j++) {
                String s = as[j];
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }

        }
        return false;
    }

    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name == null || name.isEmpty()) {
            return "";
        }
        if (!name.contains("_")) {
            return (new StringBuilder()).append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
        }
        String camels[] = name.split("_");
        String as[] = camels;
        int i = as.length;
        for (int j = 0; j < i; j++) {
            String camel = as[j];
            if (!camel.isEmpty()) {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '_') {
                upperCase = true;
                continue;
            }
            if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String replaceIgnoreCase(String text, String searchString, String replacement) {
        String lowerCaseText = text.toLowerCase();
        String lowerCaseSearchString = searchString.toLowerCase();
        StringBuilder sb = new StringBuilder(text);
        int searchStart = 0;
        int modifierPerReplacement = replacement.length() - searchString.length();
        int sbDrift = 0;
        for (int finding = lowerCaseText.indexOf(lowerCaseSearchString, searchStart); finding >= 0; finding = lowerCaseText.indexOf(lowerCaseSearchString, searchStart)) {
            sb.replace(finding + sbDrift, finding + sbDrift + searchString.length(), replacement);
            sbDrift += modifierPerReplacement;
            searchStart = finding + searchString.length();
        }

        return sb.toString();
    }

}
