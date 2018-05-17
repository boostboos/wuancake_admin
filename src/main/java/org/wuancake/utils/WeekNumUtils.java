package org.wuancake.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * 获取截止到现在的最大周数
     *
     * @return
     */
    public static Integer getMaxWeekNum() {
        return (int) ((new Date().getTime() - FIRST_DAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
    }

    /**
     * 判断是否是保护期
     *
     * @return
     */
    public static Boolean isProtected(Date time2Wuanlife) {
       

        return true;
    }

}
