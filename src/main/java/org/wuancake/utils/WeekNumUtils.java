package org.wuancake.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 与周报相关的工具类，如判断当前第几周，是否处于保护期等
 */
public class WeekNumUtils {

    private static final Date FIRST_DAY;

    static {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = format.parse("2015-10-26 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FIRST_DAY = d;
    }

    private WeekNumUtils() {
    }


    public static Integer getMaxWeekNum() {
        return (int) ((new Date().getTime() - FIRST_DAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
    }


    public static Boolean isProtected(Date time2Wuanlife) {
        /**
         * 判断思路，加入午安时间到那周末的天数和加入午安时间和现在天数作比较
         */
        //加入时间转化到Calendar
        Calendar instance = Calendar.getInstance();
        instance.setTime(time2Wuanlife);

        //加入时间是那周的星期几
        int days = instance.get(Calendar.DAY_OF_WEEK) - 1;
        if (days == 0)
            days = 7;

        //加入那周的下周一的日期
        instance.add(Calendar.DAY_OF_WEEK, 8 - days);
        Date date2NextWeek = instance.getTime();

        //日期格式化类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nowTime = new Date();

        try {
            //两个时间都需要格式化为不考虑时分秒的格式
            nowTime = simpleDateFormat.parse(simpleDateFormat.format(nowTime));
            date2NextWeek = simpleDateFormat.parse(simpleDateFormat.format(date2NextWeek));
        } catch (ParseException e) {
            //时间转化异常

        }

        return nowTime.compareTo(date2NextWeek) < 0;
    }

}
