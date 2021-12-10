package com.bjpowernode;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Test {

    public static void main(String[] args) {
        //System.out.println(ZonedDateTime.now());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        String nowTime = dateTimeFormatter.format(ZonedDateTime.now());
        System.out.println(nowTime);


        LocalTime now = LocalTime.now();
        //System.out.println(now);
        //System.out.println(now.plusHours(12));
    }
}