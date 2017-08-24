package cn.kanejin.commons.util;

import static cn.kanejin.commons.util.StringUtils.isBlank;

/**
 * 字符串数组相关的方法
 *
 * @author Kane Jin
 */
public class StringArrayUtils {
    /**
     * 把字符串数组用分割符串连起来
     *
     * @param array 字符串数组
     * @param separator 分割符
     * @return 串连起来的字符串
     */
    public static String join(String[] array, String separator) {
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
