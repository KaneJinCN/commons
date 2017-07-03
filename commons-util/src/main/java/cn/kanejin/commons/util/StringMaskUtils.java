package cn.kanejin.commons.util;

import static cn.kanejin.commons.util.StringUtils.isEmpty;

/**
 * 遮蔽敏感字符串相关的方法
 *
 * @author Kane Jin
 */
public class StringMaskUtils {
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
}
