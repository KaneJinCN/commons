package cn.kanejin.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static cn.kanejin.commons.util.StringUtils.isEmpty;
import static java.util.Calendar.*;

/**
 * 日期相关的方法
 *
 * @author Kane Jin
 */
public class DateUtils {

    /**
     * 格式化日期
     *
     * @param date 日期
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return "";

        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化日期
     *
     * @param time 毫秒数(ms) since 1970/01/01 00:00:00 GMT.
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Long time, String pattern) {
        if (time == null)
            return "";

        return formatDate(new Date(time), pattern);
    }

    /**
     * 解析日期字符串
     *
     * @param dateString 日期字符串
     * @param pattern 日期格式
     * @return 解析后的日期
     */
    public static Date parseDate(String dateString, String pattern) {
        if (isEmpty(dateString))
            return null;

        try {
            return new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }



    /**
     * 判断两个日期是否是同一天
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 如果两个日期是同一天，返回true，否则返回false
     */
    public static boolean isSameDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(d1).equals(df.format(d2));
    }


    /**
     * 获取指定日期一天的开始
     *
     * 例如：
     * "2016/10/20 18:36:58.103"的开始是"2016/10/20 00:00:00.000"
     *
     * @param date 指定日期
     * @return 一天的开始
     */
    public static Date beginOfDate(Date date) {
        if (date == null)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        startOfDay(cal);

        return cal.getTime();
    }

    /**
     * 获取指定日期一天的开始
     *
     * 例如：
     * "2016/10/20 18:36:58.103"的开始是"2016/10/20 00:00:00.000"
     *
     * @param dateString 指定日期字符串
     * @param pattern 日期格式
     * @return 一天的开始
     */
    public static Date beginOfDate(String dateString, String pattern) {
        if (isEmpty(dateString))
            return null;

        return beginOfDate(parseDate(dateString, pattern));
    }


    /**
     * 获取指定日期一天的结束
     *
     * 例如：
     * "2016/10/20 18:36:58.103"的开始是"2016/10/20 23:59:59.999"
     *
     * @param date 指定日期
     * @return 一天的结束
     */
    public static Date endOfDate(Date date) {
        if (date == null)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        endOfDay(cal);

        return cal.getTime();
    }

    /**
     * 获取指定日期一天的结束
     *
     * 例如：
     * "2016/10/20 18:36:58.103"的开始是"2016/10/20 23:59:59.999"
     *
     * @param dateString 指定日期字符串
     * @param pattern 日期格式
     * @return 一天的结束
     */
    public static Date endOfDate(String dateString, String pattern) {
        if (isEmpty(dateString))
            return null;

        return endOfDate(parseDate(dateString, pattern));
    }


    /**
     * 获取今天的开始和结束
     *
     * @return 长度为2的日期数组，[0]是今天的开始，[1]是今天的结束
     */
    public static Date[] startAndEndOfToday() {
        Date today = new Date();

        return new Date[]{beginOfDate(today), endOfDate(today)};
    }


    /**
     * 获取指定日期所在星期的开始和结束
     *
     * @param date 指定日期
     * @return 长度为2的日期数组，[0]是星期的开始，[1]是星期的结束
     */
    public static Date[] startAndEndOfWeek(Date date) {

        Date[] result = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayOfWeek = cal.get(DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0)
            dayOfWeek = 7;

        cal.add(DATE, 1 - dayOfWeek);
        startOfDay(cal);
        result[0] = cal.getTime();

        cal.add(DATE, 6);
        endOfDay(cal);
        result[1] = cal.getTime();

        return result;
    }

    /**
     * 获取当前星期的开始和结束
     *
     * @return 长度为2的日期数组，[0]是星期的开始，[1]是星期的结束
     */
    public static Date[] startAndEndOfCurrentWeek() {
        return startAndEndOfWeek(new Date());
    }

    /**
     * 获取指定日期所在月的开始和结束
     *
     * @param date 指定日期
     * @return 长度为2的日期数组，[0]是月的开始，[1]是月的结束
     */
    public static Date[] startAndEndOfMonth(Date date) {
        Date[] result = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(DATE, 1);
        startOfDay(cal);
        result[0] = cal.getTime();

        cal.add(MONTH, 1);
        cal.add(DATE, -1);
        endOfDay(cal);
        result[1] = cal.getTime();

        return result;
    }

    /**
     * 获取当前月的开始和结束
     *
     * @return 长度为2的日期数组，[0]是月的开始，[1]是月的结束
     */
    public static Date[] startAndEndOfCurrentMonth() {
        return startAndEndOfMonth(new Date());
    }


    private static int[] QUARTER_FIRST_MONTH = {JANUARY, APRIL, JULY, OCTOBER};


    /**
     * 获取指定日期所在季度的开始和结束
     *
     * @param date 指定日期
     * @return 长度为2的日期数组，[0]是季度的开始，[1]是季度的结束
     */
    public static Date[] startAndEndOfQuarter(Date date) {
        Date[] result = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(MONTH, QUARTER_FIRST_MONTH[cal.get(MONTH) / 3]);
        cal.set(DATE, 1);
        startOfDay(cal);
        result[0] = cal.getTime();

        cal.add(MONTH, 3);
        cal.add(DATE, -1);
        endOfDay(cal);
        result[1] = cal.getTime();

        return result;
    }

    /**
     * 获取当前季度的开始和结束
     *
     * @return 长度为2的日期数组，[0]是季度的开始，[1]是季度的结束
     */
    public static Date[] startAndEndOfCurrentQuarter() {
        return startAndEndOfQuarter(new Date());
    }


    /**
     * 获取指定日期所在年的开始和结束
     *
     * @param date 指定日期
     * @return 长度为2的日期数组，[0]是年的开始，[1]是年的结束
     */
    public static Date[] startAndEndOfYear(Date date) {
        Date[] result = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(MONTH, JANUARY);
        cal.set(DATE, 1);
        startOfDay(cal);
        result[0] = cal.getTime();

        cal.add(YEAR, 1);
        cal.add(DATE, -1);
        endOfDay(cal);
        result[1] = cal.getTime();

        return result;

    }

    /**
     * 获取当前年的开始和结束
     *
     * @return 长度为2的日期数组，[0]是年的开始，[1]是年的结束
     */
    public static Date[] startAndEndOfCurrentYear() {
        return startAndEndOfYear(new Date());
    }

    private static void startOfDay(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    private static void endOfDay(Calendar cal) {
        cal.set(HOUR_OF_DAY, 23);
        cal.set(MINUTE, 59);
        cal.set(SECOND, 59);
        cal.set(MILLISECOND, 999);
    }
}
