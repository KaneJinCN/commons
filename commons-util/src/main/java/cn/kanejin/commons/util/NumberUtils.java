package cn.kanejin.commons.util;

import java.math.BigDecimal;

import static cn.kanejin.commons.util.StringUtils.isEmpty;

/**
 * 数字相关的方法
 *
 * @author Kane Jin
 */
public class NumberUtils {

    /**
     * 判断字符串是否是数字
     *
     * @param str 字符串
     * @return 如果字符串全是数字，则返回true
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str))
            return false;
        return str.matches("^\\d+$");
    }

    /**
     * 判断字符串是否是小数
     *
     * @param str 字符串
     * @return 如果字符串全是数字，则返回true
     */
    public static boolean isDecimal(String str) {
        if (isEmpty(str))
            return false;
        return str.matches("^\\d+(\\.\\d+)?$");
    }

    /**
     * 把字符串转成Integer
     *
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转后的Integer
     */
    public static Integer toInt(String str, Integer defaultValue) {
        if (isEmpty(str))
            return defaultValue;

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 把字符串转成Long
     *
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转后的Long
     */
    public static Long toLong(String str, Long defaultValue) {
        if (isEmpty(str))
            return defaultValue;

        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 把字符串转成Float
     *
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转后的Float
     */
    public static Float toFloat(String str, float defaultValue) {
        if (isEmpty(str))
            return defaultValue;

        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 把字符串转成Double
     *
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转后的Double
     */
    public static Double toDouble(String str, Double defaultValue) {
        if (isEmpty(str))
            return defaultValue;

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 把字符串转成BigDecimal
     *
     * @param str 字符串
     * @param defaultValue 默认值
     * @return 转后的BigDecimal
     */
    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
        if (isEmpty(str))
            return defaultValue;

        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 比较两个BigDecimal数值，取其中小的数值
     *
     * @param a 数值a
     * @param b 数值b
     * @return 其中小的数值
     */
    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0 ? b : a;
    }

    /**
     * 比较两个BigDecimal数值，取其中大的数值
     *
     * @param a 数值a
     * @param b 数值b
     * @return 其中大的数值
     */
    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0 ? a : b;
    }

}
