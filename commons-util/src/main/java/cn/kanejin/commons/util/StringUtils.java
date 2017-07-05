package cn.kanejin.commons.util;

import java.util.Random;

/**
 * 字符串相关的方法
 *
 * @author Kane Jin
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 如果字符串为null或为空("")，返回true，否则返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return 如果字符串为null或为空("")，返回false，否则返回true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空或仅含有空格
     *
     * @param str 字符串
     * @return 如果字符串为null或为空("")或内容全是空格(" ")，返回true，否则返回false
     */
    public static boolean isBlank(String str) {
        return str == null
            || str.isEmpty()
            || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空而且含有非空格字符
     *
     * @param str 字符串
     * @return 如果字符串为null或为空("")或内容全是空格(" ")，返回false，否则返回true
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 把为null的字符串转化成空字符串（""）
     *
     * @param str 字符串
     * @return 如果字符串不为null则返回原字符串，否则返回空（""）
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int CHARS_LENGTH = CHARS.length;
    private static final Random RANDOM = new Random();

    /**
     * 生成一个指定长度的随机字符串（a-zA-Z0-9）
     *
     * @param length 字符串的长度
     * @return 一个指定长度的随机字符串
     */
    public static String random(int length) {
        if (length <= 0)
            throw new IllegalArgumentException("Length must be greater than 0 : " + length);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(CHARS[RANDOM.nextInt(CHARS_LENGTH)]);
        }
        return sb.toString();
    }

    /**
     * 生成一个指定长度的随机字符串（a-zA-Z0-9）
     *
     * @param length 字符串的长度
     * @param onlyNumber 是否是全数字，当为true时，只生成全数字的字符串，而且首字符不为'0'
     *
     * @return
     */
    public static String random(int length, boolean onlyNumber) {
        if (!onlyNumber)
            return random(length);

        if (length <= 0)
            throw new IllegalArgumentException("Argument length must be greater than 0 : " + length);
        StringBuilder sb = new StringBuilder();

        for (;;) {
            int i = RANDOM.nextInt(10);

            if (i > 0) {
                sb.append(i);
                break;
            }
        }

        for (int i = 1; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }

        return sb.toString();
    }

}
