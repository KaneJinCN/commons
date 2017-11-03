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


    /**
     * 遮蔽敏感字符串
     *
     * 遮蔽字符串中间一半长度的字符，例如：
     * "1rwLQAFbHSh9bZ5GrRwUmKSk9cLft8D1b"遮蔽之后为"1rwLQAFb****************9cLft8D1b"
     *
     * @param str 字符串
     * @return 遮蔽后的字符串
     */
    public static String maskString(String str) {
        return maskString(str, false);
    }

    /**
     * 按指定的位置和长度遮蔽敏感字符串
     *
     * 例如：
     * maskString("13901270809", 3, 4)的返回值为"139****0809"
     *
     * @param str 字符串
     * @param start 开始位置坐标
     * @param length 遮蔽长度
     * @return 遮蔽后的字符串
     */
    public static String maskString(String str, int start, int length) {
        return maskString(str, start, length, false);
    }

    /**
     * 遮蔽敏感字符串，并按要求缩短字符串
     *
     * 遮蔽字符串中间一半长度的字符，如果缩短的话，则只保留前后4位字符，例如：
     * "1rwLQAFbHSh9bZ5GrRwUmKSk9cLft8D1b"遮蔽之后为
     * 未缩短："1rwLQAFb****************9cLft8D1b"
     * 缩短：1rwL**8D1b
     *
     * @param str 字符串
     * @param shorten 是否缩短字符串
     * @return 遮蔽后的字符串
     */
    public static String maskString(String str, boolean shorten) {
        if (isEmpty(str))
            return "";

        int maskLength = 0;

        if (shorten && str.length() > 10) {
            maskLength = Math.max(str.length() - 8, 1);
        } else  {
            maskLength = Math.max(str.length() / 2, 1);
        }

        int startIndex = (str.length() - maskLength) / 2;

        return maskString(str, startIndex, maskLength, shorten);
    }

    /**
     * 按指定的位置和长度遮蔽敏感字符串
     *
     * 如果缩短为true，则遮蔽的部分只用两个**代替，例如：
     * <code>maskString("1rwLQAFbHSh9bZ5GrRwUmKSk9cL", 5, 6, true)</code>
     * 的返回值为<code>"1rwLQ**9bZ5GrRwUmKSk9cL"</code>
     *
     * @param str 字符串
     * @param start 开始位置坐标
     * @param length 遮蔽长度
     * @param shorten 是否缩短字符串
     * @return 遮蔽后的字符串
     */
    public static String maskString(String str, int start, int length, boolean shorten) {
        if (isEmpty(str))
            return "";

        if (start + length >= str.length()) {
            start = 0;
            length = str.length();
        }

        String mask = "";
        if (shorten && length > 2) {
            mask = "**";
        } else {
            for (int i = 0; i < length; i++)
                mask += "*";
        }

        return str.substring(0, start) + mask + str.substring(start + length);
    }

    /**
     * 遮蔽手机号码
     *
     * 等同于<code>maskString(mobile, 3, 4)</code>
     *
     * @param mobile 手机号码
     * @return 遮蔽后的手机号码
     */
    public static String maskMobile(String mobile) {
        return maskString(mobile, 3, 4);

    }

    /**
     * 遮蔽邮箱
     *
     * 邮箱@后面的域名不遮蔽，只遮蔽@之前的用户名，遮蔽用户名中间一半长度的字符，例如：
     * <code>kanejin.cn@gmail.com</code>遮蔽后为<code>ka*****.cn@gmail.com</code>
     *
     * @param email 邮箱
     * @return 遮蔽后的邮箱
     */
    public static String maskEmail(String email) {
        if (isEmpty(email))
            return email;

        if (!email.contains("@"))
            return maskString(email);

        String name = email.substring(0, email.indexOf("@"));

        return maskString(name) + email.substring(email.indexOf("@"));
    }


    /**
     * 把字符串数组用分割符串连起来
     *
     * @param array 字符串数组
     * @param separator 分割符
     * @return 串连起来的字符串
     */
    public static String joinArray(String[] array, String separator) {
        if (array == null)
            return "";
        if (separator == null)
            separator = "";

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            buf.append(array[i]);
            if (i >= array.length - 1)
                continue;
            buf.append(separator);
        }

        return buf.toString();
    }

    /**
     * 拼接路径
     * <p>
     * 把一些路径拼接起来，比如：
     * 把"http://www.demo.com/", "/user", "/get"
     * 拼接成"http://www.demo.com/user/get"
     *
     * @param pieces 需要拼接的路径
     * @return
     */
    public static String joinPath(String... pieces) {
        if (pieces == null || pieces.length <= 0)
            return "";

        String result = null;
        for (int i = 0; i < pieces.length; i++) {
            if (isBlank(pieces[i]))
                continue;

            if (isBlank(result)) {
                result = pieces[i];
                continue;
            }

            if (result.endsWith("/"))
                if (pieces[i].startsWith("/"))
                    result += pieces[i].substring(1);
                else
                    result += pieces[i];
            else
            if (pieces[i].startsWith("/"))
                result += pieces[i];
            else
                result += "/" + pieces[i];
        }

        return result;
    }
}
