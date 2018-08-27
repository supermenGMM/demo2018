package com.mm.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDemo {
    public static void main(String[] args){
        System.out.println("2018-08-10T10:10:10+02:00[Asia/Beijing]".charAt(25));
        ZonedDateTime zone = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");

        System.out.println(zone);

        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println("defaultZone:"+defaultZone);

        ZoneId beijing = ZoneId.of("Europe/Paris");
        System.out.println(beijing);


    }
}
