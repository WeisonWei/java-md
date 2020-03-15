package com.back.java8;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    @Test
    public void timestamp() {

        //方法 一
        long l = System.currentTimeMillis();
        //方法 二
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        //方法 三
        long time = new Date().getTime();

        long time1 = 1571396400000l;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time1))));
        System.out.println("格式化结果：" + sd);


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
        String sd2 = sdf1.format(new Date(Long.parseLong(String.valueOf(time1))));
        System.out.println("格式化结果：" + sd2);

    }

    @Test
    public void LocalDate() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;

        //LocalTime
        LocalTime localTimeUtc = LocalTime.now(ZoneOffset.UTC);
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = localTime.plusNanos(100);
        LocalTime localTime2 = localTime.plusSeconds(10);
        LocalTime localTime3 = localTime.plusMinutes(5);
        LocalTime localTime4 = localTime.minusHours(1);
        String format1 = localTime4.format(timeFormatter);
        //设置时区
        OffsetTime offsetTime = localTime4.atOffset(ZoneOffset.UTC);
        OffsetTime offsetTime1 = localTime4.atOffset((ZoneOffset.of("+8")));
        //生成指定时间
        LocalTime sixThirty = LocalTime.parse("06:30");
        LocalTime sixThirty1 = LocalTime.of(6, 30);
        //获取时间毫秒
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();


        //LocalDate
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.plusDays(1);
        LocalDate localDate2 = localDate1.minusDays(1);
        LocalDate localDate3 = localDate1.minusMonths(1);
        LocalDate localDate5 = localDate1.minusWeeks(1);
        LocalDate localDate4 = localDate1.minusYears(1);

        String format2 = localDate4.format(isoDateTime);

        //LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.minusDays(1);
        String format = localDateTime1.format(dateTimeFormatter);

    }

    @Test
    public void MilliSecondsCovertToLocalDateTime() {
        long laaaa = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime time2 = LocalDateTime.ofEpochSecond(laaaa, 0, ZoneOffset.UTC);
        LocalDateTime time3 = LocalDateTime.ofEpochSecond(1571396400l, 0, ZoneOffset.of("+8"));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        System.out.println("-----1--------->" + time2.format(dateTimeFormatter));
        System.out.println("-----2--------->" + time3.format(dateTimeFormatter));
    }
}
