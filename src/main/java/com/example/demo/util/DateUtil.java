package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    /**
     * 时间格式XXXX年X月X日X时X分X秒
     * 将字符串转化为时间
     *
     * @param string
     * @return
     */
    public static Date process(String string) {
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder(string);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '年' || stringBuilder.charAt(i) == '月' ||
                    stringBuilder.charAt(i) == '日' || stringBuilder.charAt(i) == '时' ||
                    stringBuilder.charAt(i) == '分') {
                stringBuilder.setCharAt(i, '-');
            }
            if (stringBuilder.charAt(i) == '秒') {
                stringBuilder.deleteCharAt(i);
            }
        }
        try {
            date = fmt.parse(new String(stringBuilder));
        } catch (Exception e) {

        }
        return date;
    }

    public static String processDateToString(Date date) {
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return a.format(date);
    }

    public static Date processStringToDate(String string) {
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder(string);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '年' || stringBuilder.charAt(i) == '月') {
                stringBuilder.setCharAt(i, '-');
            }
            if (stringBuilder.charAt(i) == '日') {
                stringBuilder.deleteCharAt(i);
            }
        }
        try {
            date = fmt.parse(new String(stringBuilder));
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 计算两个时间相隔多少天
     *
     * @param start
     * @param end
     * @return
     */
    public static long countDay(Date start, Date end) {
        long e = end.getTime();
        long s = start.getTime();
        long betweenDate = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
        return betweenDate;
    }

    /**
     * 计算两个时间相隔多少小时
     *
     * @param start
     * @param end
     * @return
     */
    public static int countHour(Date start, Date end) {
        long e = end.getTime();
        long s = start.getTime();
        return (int) Math.abs(end.getTime() - start.getTime()) / (1000 * 60 * 60);
    }

    /**
     * 判断这个时间是否在高峰期8:00-20:00
     *
     * @param date
     * @return
     */
    public static Boolean isPeak(Date date) {
        return getTimeByDate(date, 8).getTime() <= date.getTime() && date.getTime() <= getTimeByDate(date, 20).getTime();
    }

    /**
     * 获取当前日期具体几点的Date类
     *
     * @param date
     * @return
     */
    public static Date getTimeByDate(Date date, int hour) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, hour);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 计算当前时间的下一个收费时段
     */
    public static Date getNextChargeDate(Date date) {
        if (DateUtil.isPeak(date)) {
            return DateUtil.getTimeByDate(date, 20);
        }
        return DateUtil.getTimeByDate(date, 8);
    }

    /**
     * 计算当前时间的上一个收费时段
     */
    public static Date getLastChargeDate(Date date) {
        //判断是否为高峰,如果是的话返回当天早上8点的Date
        if (DateUtil.isPeak(date)) {
            return DateUtil.getTimeByDate(date, 8);
        }
        //如果不是,分为两种情况:前一天的晚上8点和晚上8点
        if (date.getTime() > getTimeByDate(date, 0).getTime()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTime();
        }
        return DateUtil.getTimeByDate(date, 20);
    }

    /**
     * 当前时间加一个小时
     */
    public static Date getNextHourDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 1);
        if (ca.getTime().getTime() < date.getTime()) {
            ca.add(Calendar.DAY_OF_WEEK, 1);
        }
        return ca.getTime();
    }

    /**
     * 当前时间加一天
     */
    public static Date getNextDayDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_WEEK, 1);
        return ca.getTime();
    }

    /**
     * 当前时间前一天
     */
    public static Date getLastDayDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_WEEK, -1);
        return ca.getTime();
    }

    public static Date getLastDayDateByNum(int i) {
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_WEEK, -i);
        return ca.getTime();
    }

    /**
     * 判断当前日期是否为周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 将日期格式化为XXXX年X月X日
     *
     * @param date
     * @return
     */
    public static String dateFormatMonth(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = "";
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {

        }
        return dateStr;
    }

    /**
     * 将日期格式化为XXXX年X月
     *
     * @param date
     * @return
     */
    public static String dateFormatYear(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        String dateStr = "";
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {

        }
        return dateStr;
    }

    /**
     * 获取当前时间并且转为string输出
     *
     * @return
     */
    public static String getNowDate() {
        String dateStr = "";
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String getNowDate2() {
        String dateStr = "";
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将时间类变为XX-XX-XX
     *
     * @param date
     * @return
     */
    public static String getNowDate(Date date) {
        String dateStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将XX-XX-XX变为时间类
     *
     * @param string
     * @return
     */
    public static Date getNowDate(String string) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = fmt.parse(string);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 当前时间推进直到当天的０点或者结束时间
     *
     * @param date
     * @param ENDING_TIME
     * @return
     */
    public static Date addDay(Date date, Date ENDING_TIME) {
        Date date1 = getTimeByDate(getNextDayDate(date), 0);
        return ENDING_TIME.getTime() > date1.getTime() ? date1 : ENDING_TIME;
    }


    public static String dateDiff(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        diff = endTime.getTime() - startTime.getTime();
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh + day * 24;// 计算差多少小时
        min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm / ns;// 计算差多少秒
        return day + "天" + (hour - day * 24) + "小时"
                + (min - day * 24 * 60) + "分钟" + sec + "秒。";
    }

    public static Date getLastMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
