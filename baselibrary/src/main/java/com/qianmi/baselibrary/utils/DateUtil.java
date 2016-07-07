package com.qianmi.baselibrary.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * http://www.ablanxue.com/prone_14548_1.html
 * Created by Chen Haitao on 2016/1/19.
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyyMMdd";
    public static String LINE_FORMAT = "yyyy-MM-dd";
    public static String POINT_FORMAT = "yyyy.MM.dd";

    /**
     * 测试主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1951; i < 1960; i++) {
            System.out.println(formatDate(getYearFirst(i)));
            System.out.println(formatDate(getYearLast(i)));
        }

        System.out.println(formatDate(getCurrYearFirst()));
        System.out.println(formatDate(getCurrYearLast()));

    }

    /**
     * 格式化日期
     *
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(LINE_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 格式化日期
     *
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDateDefault(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 格式化日期
     *
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDatePoint(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(POINT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取当前时间
     *
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TRADITIONAL_CHINESE);
        return format.format(date);
    }


    /**
     * 获取当前时间
     *
     * @param date
     * @return
     */
    public static String getTimeDayString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.TRADITIONAL_CHINESE);
        return format.format(date);
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的周的第一天
     *
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime();
    }

    /**
     * 返回指定日期的周的最后一天
     *
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Sunday
        return calendar.getTime();
    }

    /**
     * 判定查询的一周中是否包含当天
     */
    public static boolean containTodayOfWeek(Date startDate, Date endDate) {
        Date today = new Date();
        return startDate.getTime() < today.getTime() && endDate.getTime() > today.getTime();
    }

    /**
     * 根据年月获取该月份的天数
     *
     * @return
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int dayOfMonth = calendar.getActualMaximum(Calendar.DATE);
        return dayOfMonth;
    }

    /**
     * 获取月跨度列表
     *
     * @param startId
     * @return
     */
    public static ArrayList<String> getMonthList(int startId) {
        ArrayList<String> list = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        String startDate = "";
        String endDate = "";
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        for (int i = 0; i < startId; i++) {
            //月份显示两位数
            String strMonth = month < 10 ? "0" + month : "" + month;
            startDate = year + "." + strMonth + "." + "01";
            endDate = year + "." + strMonth + "." + getDayOfMonth(year, month);
            if (month > 1) {
                month--;
            } else {
                year--;
                month = 12;
            }
            list.add(startDate + " ~ " + endDate);
        }
        return list;
    }

    /**
     * 获取周日期列表
     */
    public static ArrayList<String> getWeekList(int startId) {
        ArrayList<String> list = new ArrayList<String>();
        Date today = new Date();
        String startDate = "";
        String endDate = "";
        for (int i = 0; i < startId; i++) {
            startDate = dateToString(getWeekList(today).get(0));
            endDate = dateToString(getWeekList(today).get(6));
            Long fTime = today.getTime() - 7 * 24 * 3600000;
            today.setTime(fTime);
            list.add(startDate + " ~ " + endDate);
        }
        return list;
    }

    private static String dateToString(Date mdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = sdf.format(mdate);
        return strDate;
    }

    private static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }

    private static ArrayList<Date> getWeekList(Date mdate) {

        ArrayList<Date> days = new ArrayList<>();
        days.addAll(dateToWeek(mdate));

        Calendar calendar = Calendar.getInstance();
        int lastIndex = days.size() - 1;
        Date startDay = days.get(0);
        Date endDay = days.get(lastIndex);

        calendar.setTime(startDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        days.remove(0);
        days.add(0, calendar.getTime());

        calendar.clear();
        calendar.setTime(endDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        days.remove(lastIndex);
        days.add(lastIndex, calendar.getTime());

        return days;
    }

    /**
     * 获取最近多少天时间
     *
     * @param day
     * @return
     */
    public static Date getLastDate(int day) {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        today = calendar.getTime();
        switch (day) {
            case 7:
                today = getFirstDayOfWeek(today);
                break;
            case 30:
                today = getFirstDayOfMonth(today);
                break;
        }
        return today;
    }

    /**
     * 获取指定日期
     *
     * @param day
     * @return
     */
    public static Date getFirstDate(Date date, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        switch (day) {
            case 7:
                calendar.add(Calendar.DAY_OF_MONTH, -day);
                date = calendar.getTime();
                return date;
            case 30:
                return getFisrtDayOfLastMonth(date);
        }

        return date;
    }

    /**
     * 获取指定日期
     *
     * @param day
     * @return
     */
    public static Date getLastDate(Date date, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        switch (day) {
            case 7:
                calendar.add(Calendar.DAY_OF_MONTH, -day);
                date = calendar.getTime();
                return date;
            case 30:
                //获取上个月的时间进行比较
                Calendar lastCal = Calendar.getInstance();
                lastCal.setTime(getLastDayOfLastMonth(date));

                if (calendar.get(Calendar.DAY_OF_MONTH) > lastCal.get(Calendar.DAY_OF_MONTH)) {
                    return getLastDayOfLastMonth(date);
                } else {
                    calendar.add(Calendar.MONTH, -1);
                    date = calendar.getTime();
                    return date;
                }
        }

        return date;
    }

    /**
     * 获取上月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFisrtDayOfLastMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取上月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取最近多少天时间
     *
     * @param dataType
     * @return
     */
    public static Date getLastDate(String dataType) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        today = calendar.getTime();
        return today;
    }

    /**
     * 获取指定日期的最近多少天时间
     *
     * @param dataType
     * @return
     */
    public static Date getLastDate(Date date, String dataType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (dataType) {
            case "W":
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                date = calendar.getTime();
                return date;
            case "M":
                calendar.add(Calendar.MONTH, -1);
                date = calendar.getTime();
                return date;
        }
        return date;

    }

    /**
     * 判断某天是否为周一
     *
     * @param date
     * @return
     */
    public static boolean isWeekFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (2 == calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 判断某天是否为1日
     *
     * @param date
     * @return
     */
    public static boolean isMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (1 == calendar.get(Calendar.DAY_OF_MONTH));
    }


}
