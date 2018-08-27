package com.mm.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateDemo1 {
    public static void main(String[] args){
        DateDemo1 dateDemo1 = new DateDemo1();
        dateDemo1.testLocalDateTime();
    }
    public  void testLocalDateTime(){
        LocalDateTime currenTime = LocalDateTime.now();
        System.out.println("当前时间:" + currenTime);

        LocalDate localDate = currenTime.toLocalDate();
        System.out.println("date1：" + localDate);

        Month month = currenTime.getMonth();
        int dayOfMonth = currenTime.getDayOfMonth();
        int second = currenTime.getSecond();

        System.out.println("月：" + month + ",日:" + dayOfMonth + ",秒：" + second);
        //对时间进行修改操作
        LocalDateTime date2 = currenTime.withDayOfMonth(10).withYear(2016);
        System.out.println("date2" + date2);

        //2 JANUARY 2008
        LocalDate date3 = LocalDate.of(2008,Month.JANUARY,2);
        System.out.println("date3"+date3);

        // 22小时 15分钟
        LocalDate parse = LocalDate.parse("2018-05-04");
        LocalTime time = LocalTime.parse("22:15:00");
        System.out.println("date4:"+parse+","+time);
    }
}
