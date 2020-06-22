package com.zjy.zwtcount.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ziyi on 2018/7/26.
 */
@Slf4j
public class DateConverUtil {

    public static Date getRecordTime(){
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e){
            log.error("【日期转换】错误,msg={}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getCurrentTimeStr(String format){
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //获取String类型的时间
        return sdf.format(date);
    }

    public static String formatDateToStr(Date date, String format){
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        //获取String类型的时间
        return sdf.format(date);
    }

    public static Long getYearStartTimestamp(String year){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            return sdf.parse(year).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Long getYearEndTimestamp(String year){
        year = String.valueOf(Integer.valueOf(year) + 1);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            return sdf.parse(year).getTime() - 1;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Date getStrToDate(String times){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(times);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date getStringToDate(String times){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(times);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static int calcAmOrPm(Date date) {
        try {
            String am = formatDateToStr(date, "yyyy-MM-dd") + " 12:00:00";
            if(getStrToDate(am).getTime() >= date.getTime()){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static String getWeek(Date date, String pre){
        try {
            String[] weeks = {"日","一","二","三","四","五","六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(week_index<0){
                week_index = 0;
            }
            return (pre + weeks[week_index]);
        }catch (Exception e){
            return null;
        }
    }

    public static String getWeek(String date){
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

            String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(f.parse(date));
            int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(week_index<0){
                week_index = 0;
            }
//            System.out.println(weeks[week_index]);
            return weeks[week_index];
        }catch (Exception e){
            return null;
        }
    }


    public static String addMonth(Date date,Integer addMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return  sdf.format(calendar.getTime());
    }

   public  static Long  getYearMonthDay(){
       try {
           Date currentTime = new Date();
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           String strDate = formatter.format(currentTime);
           Date currentDate = formatter.parse(strDate);
           return currentDate.getTime();
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return null;
   }
    public  static String  getYearMonthDayString(){
        try {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(currentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTime(String charset){
        if(StringUtils.isEmpty(charset)){
            charset = "yyyyMMddHHmmss";
        }
        DateFormat df = new SimpleDateFormat(charset);
        Calendar calendar = Calendar.getInstance();
        return  df.format(calendar.getTime());
    }

    public static void main(String[] args) {
//        log.info(getYearStartTimestamp("2019").toString());
//        log.info(getYearEndTimestamp("2019").toString());
        System.out.println(formatDateToStr(new Date(),"yyyyMMdd"));

     //   System.out.println(addMonth(new Date(),-3));
    }
}
