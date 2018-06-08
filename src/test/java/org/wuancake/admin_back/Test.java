//package org.wuancake.admin_back;
//
//import org.wuancake.utils.MD5Utils;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class Test {
//
//    public void test1() {
//        //加入时间
//        Date enterTime = new Date("2018/5/1");
//
//        Calendar instance = Calendar.getInstance();
//        Date nowTime = instance.getTime();//当前时间
//
//        instance.setTime(enterTime);
//
//        int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK) - 1;
//        if (dayOfWeek == 0) {
//            dayOfWeek = 7;
//        }
//        System.out.println("加入的时候是星期" + dayOfWeek);
//
//        Long days2NextWeek = (8 - dayOfWeek) * 1000L * 60 * 60 * 24;
//
//        Date nextWeekDate = new Date(enterTime.getTime() + days2NextWeek);
//
//        System.out.println(nextWeekDate.toLocaleString());
//    }
//
//    public void test2() {
//        Date enterTime = new Date();
//
//        Calendar instance = Calendar.getInstance();
//
//        instance.setTime(enterTime);
//
//        int days = instance.get(Calendar.DAY_OF_WEEK) - 1;
//        if (days == 0) {
//            days = 7;
//        }
//        instance.add(Calendar.DAY_OF_WEEK, 8 - days);
//
//        Date date2NextWeek = instance.getTime();
//
//        Date nowTime = new Date("2018/5/20");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(nowTime);
//        Date parse = null;
//        try {
//            parse = simpleDateFormat.parse(format);
//            date2NextWeek = simpleDateFormat.parse(simpleDateFormat.format(date2NextWeek));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println("date2NextWeek:" + date2NextWeek.toLocaleString());
//        System.out.println("nowTime:" + parse.toLocaleString());
//
//        System.out.println(nowTime.compareTo(date2NextWeek));
//
//    }
//
//    @org.junit.Test
//    public void testMd5(){
//        System.out.println(MD5Utils.generate("tt"));
//    }
//}
