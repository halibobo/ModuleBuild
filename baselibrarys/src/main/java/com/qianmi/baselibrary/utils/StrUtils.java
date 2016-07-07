package com.qianmi.baselibrary.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chen Haitao on 2015/7/23.
 */
public class StrUtils {
    public static final String REPORT_MEMBER_CONSUME_SEARCH_KEY = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\s]{1,50}$";  // 必须为1-50个字符(中文、数字和英文)
    public static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";   // 手机号;
    public static final String TRUE_NAME = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\s]{2,15}$";  // 必须为2-15个字符(中文、数字和英文)
    public static final String USER_NAME = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\-_\\s]{3,25}$";  // 必须为3-25个字符(支持中文、英文、数字及-和_)
    //public static final String PASSWD = "^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[0-9a-zA-Z\\W]\\S{6,20}$";  // 6-20位字符，包含字母、数字或符号中两种
    public static final String PASSWD = "^[0-9a-zA-Z\\W]\\S{5,20}$";  // 6-20位字符，支持字母、数字或符号的组合
    public static final String DEVICE_NO = "^[0-9a-zA-Z]{1,6}$";  // 1-6位字符，支持字母、数字
    public static final String DITITAL = "^[0-9.]+$";  //


    public static String getText(String text) {
        if (text == null) {
            return "";
        }
        return text.trim();
    }

    public static boolean isNull(String str) {
        return str == null || str.length() == 0 || str.trim().equalsIgnoreCase("null");
    }

    public static boolean isNotNull(String str) {
        return str != null && str.length() > 0 && (!str.trim().equalsIgnoreCase("null"));
    }

    public static String getDecimalFormatStr(double original) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(original);
    }


    public static boolean isMobileNum(String phone) {
        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(phone);
        return m.find();
    }

    public static boolean match(String matcher, String str) {
        Pattern p = Pattern.compile(matcher);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDigital(String d) {
        return match(DITITAL, d);
    }


    public static String getSignStr(Object object) {
        if (object == null) {
            return "";
        }
        return String.format("%.2f", object);
    }

    public static String getSignStr(double d) {
        return String.format("%.2f", d);
    }


    public static String getRandomBarCode() {
        StringBuilder str = new StringBuilder("66");
        int a[] = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (10 * (Math.random()));
            str.append(a[i]);
        }
        str.append(getCheckCode(str.toString()));
        return str.toString();
    }

    private static String getCheckCode(String string) {
        int evenCount = 0;
        int oddCount = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            int j = string.length() - i;
            if (j % 2 == 1) {
                evenCount += Integer.parseInt(string.substring(i, i + 1));
            } else {
                oddCount += Integer.parseInt(string.substring(i, i + 1));
            }
        }
        return String.valueOf((evenCount * 3 + oddCount) % 10);
    }

    public static void setTextStyle(Context ctx, TextView tx, int resId, int val, int styleId, int start, int end) {
        SpannableString styledText = new SpannableString(String.format(ctx.getString(resId), String.valueOf(val)));
        styledText.setSpan(new TextAppearanceSpan(ctx, styleId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tx.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void setTextStyle(Context ctx, TextView tx, int resId, long val, int styleId, int start, int end) {
        SpannableString styledText = new SpannableString(String.format(ctx.getString(resId), String.valueOf(val)));
        styledText.setSpan(new TextAppearanceSpan(ctx, styleId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tx.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void setTextStyle(Context ctx, TextView tx, int resId, double val, int styleId, int start, int end) {
        SpannableString styledText = new SpannableString(String.format(ctx.getString(resId), StrUtils.getDecimalFormatStr(val)));
        styledText.setSpan(new TextAppearanceSpan(ctx, styleId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tx.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static void setTextStyle(Context ctx, TextView tx, int resId, String val, int styleId, int start, int end) {
        SpannableString styledText = new SpannableString(String.format(ctx.getString(resId), val));
        styledText.setSpan(new TextAppearanceSpan(ctx, styleId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tx.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    public static String setGetPhoneMask(String input) {
        if (StrUtils.isNotNull(input)) {
            String maskNumber = input.substring(0, 3) + "****" + input.substring(7, input.length());
            return maskNumber;
        }
        return "";
    }

    /**
     * 时间戳毫秒数转时间字符串
     *
     * @param mill
     * @return
     */
    public static String timestampToDate(long mill) {
        Date date = new Date(mill);
        String strs = String.valueOf(mill);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.getDefault());
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }


    public static Date stringToDate(String str, String pattern) {
        return new SimpleDateFormat(pattern).parse(str, new ParsePosition(0));
    }

    public static String getFormatedDistance(double distance) {
        if (distance < 100.0) {
            return "<100米";
        } else if (distance >= 100.0 && distance <= 1000.0) {
            return String.valueOf((int) distance) + "米";
        } else if (distance > 1000.0 && distance <= 20000.0) {
            return getDecimalFormatStr(distance / 1000.0) + "公里";
        } else {
            return ">20公里";
        }
    }

    public static boolean inSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        return (year1 == year2) && (day1 == day2);
    }

    /**
     * 获取两个时间的时间差 如1天2小时30分钟
     */
    public static String getDateElapsed(Date endDate, Date startDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 保留两位小数
     */
    public static double getRound(double _value) {
        return Math.round(_value * 100) * 0.01;
    }

    /**
     * 获得字符串的长度，中文算2个，英文算1个
     *
     * @param text
     * @return
     */
    public static int getStringLength(String text) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < text.length(); i++) {
            String temp = text.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static int toInt(String value) {
        try {
            if (isNotNull(value)) {
                return Integer.parseInt(value);
            }
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static float toFloat(String value) {
        try {
            if (isNotNull(value)) {
                return Float.parseFloat(value);
            }
            return 0.0f;
        } catch (NumberFormatException e) {
            return 0.00f;
        }
    }

    public static double toDouble(String value) {
        try {
            if (isNotNull(value)) {
                return Double.parseDouble(value);
            }
            return 0.0f;
        } catch (NumberFormatException e) {
            return 0.00f;
        }
    }

    /**
     * 小数转化为百分数,保留两位小数
     *
     * @param rate
     * @return
     */
    public static String decimalToPercent(double rate) {

        double rateStr = rate * 100;

        return StrUtils.getSignStr(rateStr) + "%";

    }
}
