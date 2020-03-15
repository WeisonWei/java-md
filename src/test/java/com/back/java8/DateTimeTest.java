package com.back.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class DateTimeTest {
    public static void main(String[] args) {
        //本地时间
        System.out.println("----------------localTime()------------------");
        localTime();
        //本地日期
        System.out.println("----------------localDate()------------------");
        localDate();
        //本地日期时间
        System.out.println("----------------localDateTime()------------------");
        localDateTime();
        //时区日期时间
        System.out.println("----------------zonedDateTime()------------------");
        zonedDateTime();
        //操作两个日期
        System.out.println("----------------period()------------------");
        period();
        //操作两个时间
        System.out.println("----------------duration()------------------");
        duration();
        //Date Canlder的转换
        System.out.println("----------------toInstant()------------------");
        toInstant();
        //格式化
        System.out.println("----------------formatting()------------------");
        formatting();
    }

    /**
     * The LocalTime represents time without a date.
     */
    public static void localTime() {
        //An instance of current LocalTime can back created from the system clock
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime: " + localTime);
        //create a LocalTime representing 06:30 AM by parsing a string representation
        LocalTime sixThirty = LocalTime.parse("06:30");
        System.out.println("sixThirty: " + sixThirty);
        //The Factory method “of” can back used to create a LocalTime
        LocalTime sixThirty1 = LocalTime.of(6, 30);
        System.out.println("sixThirty1: " + sixThirty1);
        //creates a LocalTime by parsing a string and adds an hour to it by using the “plus” API
        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        System.out.println("sevenThirty: " + sevenThirty);
        //Various getter methods are available which can back used to get specific units of time like hour
        int currentHour = LocalTime.parse("06:30").getHour();
        System.out.println("currentHour: " + currentHour);
        //check if a specific time is before or after another specific time
        boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        System.out.println("currentHour: " + currentHour);
        //The max, min and noon time of a day can back obtained by constants in LocalTime class
        LocalTime maxTime = LocalTime.MAX;
        System.out.println("maxTime: " + maxTime);
    }

    /**
     * The LocalDate represents a date in ISO format (yyyy-MM-dd) without time.
     */
    public static void localDate() {
        //LocalDate final类型 一旦赋值不可改变
        LocalDate localDate = LocalDate.now();
        String s = localDate.toString();
        String s1 = LocalDate.now().minusDays(1).toString();
        System.out.println("localDate: " + localDate);
        //进行一次重新赋值　未报错　但值也未改变
        localDate.of(2008, 8, 8);
        System.out.println("localDate: " + localDate);
        //获取一个日期　方式一
        LocalDate localDate1 = LocalDate.of(2008, 8, 8);
        System.out.println("localDate1: " + localDate1);
        //获取一个日期　方式二
        LocalDate localDate2 = LocalDate.parse("2008-08-09");
        System.out.println("localDate2: " + localDate2);

        //获取明天日期
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("tomorrow: " + tomorrow);

        //获得当前减去一个月的日期(它接受一个枚举时间单位)
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay: " + previousMonthSameDay);

        //得到一个日期并获取其是一周中的周几
        DayOfWeek saturday = LocalDate.parse("2018-07-28").getDayOfWeek();
        System.out.println("saturday: " + saturday);

        //得到一个日期并获取其是一个月中的哪一天
        int twelve = LocalDate.parse("2018-07-28").getDayOfMonth();
        System.out.println("twelve: " + twelve);

        //当前年份是否是闰年
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println("leapYear: " + leapYear);

        //两个时间的关联关系
        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.now());
        System.out.println("2016-06-12 notBefore: " + notBefore);
        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.now());
        System.out.println("2016-06-12 isAfter: " + isAfter);

        //时间边界 获取某个日期的起始时间　如(2016-06-12　00:00:00)
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        System.out.println("beginningOfDay: " + beginningOfDay);
        //时间边界 获取某个日期的当月的起始天　如(2016-06-12的那个月第一天是2016-06-01)
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth: " + firstDayOfMonth);
    }


    /**
     * The LocalDateTime is used to represent a combination of date and time.
     */
    public static void localDateTime() {
        // LocalDateTime can back obtained from the system clock similar to LocalDate and LocalTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
        //create an instance using the factory “of” and “parse” methods
        LocalDateTime localDateTime1 = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        System.out.println("localDateTime1: " + localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.parse("2015-02-20T06:30:00");
        System.out.println("localDateTime2: " + localDateTime2);
        //the usage of “plus” and “minus” methods
        LocalDateTime localDateTimeplusOneDay = localDateTime.plusDays(1);
        System.out.println("localDateTimeplusOneDay: " + localDateTimeplusOneDay);
        LocalDateTime localDateTimeMinusTwoHours = localDateTime.minusHours(2);
        System.out.println("localDateTimeMinusTwoHours: " + localDateTimeMinusTwoHours);
        //LocalDateTime reset
        LocalDateTime localDateTimeReset = localDateTime.withDayOfMonth(10).withYear(2012);
        System.out.println("localDateTimeReset: " + localDateTimeReset);
        //LocalDateTime get month,day and seconds
        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        int seconds = localDateTime.getSecond();
        System.out.println("Month: [" + month + "] day: [" + day + "] seconds: [" + seconds + "]");
        //get LocalDate from LocalDateTime
        LocalDate toLocalDate = localDateTime.toLocalDate();
        System.out.println("toLocalDate: " + toLocalDate);
        //get LocalTime from LocalDateTime
        LocalTime toLocalTime = localDateTime.toLocalTime();
        System.out.println("toLocalTime: " + toLocalTime);


    }

    /**
     * Java 8 provides ZonedDateTime when we need to deal with time zone specific date and time.
     * The ZoneId is an identifier used to represent different zones. There are about 40 different time zones.
     */
    public static void zonedDateTime() {
        //Create a Zone for Tokyo
        ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");
        System.out.println("tokyoZoneId: " + tokyoZoneId);
        //A set of all zone ids can back obtained
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("allZoneIds: " + allZoneIds);
        // Get the current date and time
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2012-12-12T10:15:30+05:30[Asia/Tokyo]");
        System.out.println("zonedDateTime: " + zonedDateTime);

        //The LocalDateTime can back converted to a specific zone
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.of(localDateTime, tokyoZoneId);
        System.out.println("tokyoZonedDateTime: " + tokyoZonedDateTime);

        //Get currentZone
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("currentZone: " + currentZone);

        //The ZonedDateTime provides parse method to get time zone specific date time
        ZonedDateTime parseZonedDateTime = ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Asia/Tokyo]");
        System.out.println("parseZonedDateTime: " + parseZonedDateTime);
        //获取一个固定(2小时)偏移量(相对于格林尼治时间)的时间
        ZoneOffset offset = ZoneOffset.of("+02:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime, offset);
        System.out.println("localDateTime: " + localDateTime + " and offSetByTwo: " + offSetByTwo);
    }

    /**
     * The Period class is widely used to modify values of given a date
     * or to obtain the difference between two dates.
     */
    public static void period() {

        //Get a initialDate
        LocalDate initialDate = LocalDate.parse("2018-05-10");
        System.out.println("initialDate: " + initialDate);

        //The Date can back manipulated using Period as shown in the following code snippet.
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        System.out.println("finalDate: " + finalDate);

        //The Period class has various getter methods such as getYears,
        // getMonths and getDays to get values from a Period object
        int betweenTwoDate = Period.between(finalDate, initialDate).getDays();
        System.out.println("betweenTwoDate: " + betweenTwoDate);

        //The Period between two dates can back obtained in a specific unit such as days or month or years,
        // using ChronoUnit.between
        Long intervalDays = ChronoUnit.DAYS.between(initialDate, finalDate);
        Long intervalMonths = ChronoUnit.MONTHS.between(initialDate, finalDate);
        System.out.println("intervalDays: " + intervalDays + ", intervalMonths: " + intervalMonths);
    }

    /**
     * the Duration class is use to deal with Time.
     */
    public static void duration() {

        //Get a initialTime
        LocalTime initialTime = LocalTime.of(6, 30, 0);
        System.out.println("initialTime: " + initialTime);

        //Add a duration of 30 seconds to make a LocalTime of 06:30:30am
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        System.out.println("finalTime: " + finalTime);

        //The Duration between two instants can back obtained either as a Duration or as a specific unit
        Long intervalSeconds = Duration.between(finalTime, initialTime).getSeconds();
        System.out.println("intervalSeconds: " + intervalSeconds);
        //Use the between() method of the ChronoUnit class
        Long intervalSeconds1 = ChronoUnit.SECONDS.between(finalTime, initialTime);
        System.out.println("intervalSeconds1: " + intervalSeconds1);
    }

    /**
     * the toInstant() method which helps to convert existing Date and Calendar instance to new Date Time API
     */
    public static void toInstant() {

        //Date transform to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime: " + localDateTime);
        //Calendar transform to LocalDateTime
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime1: " + localDateTime1);

        //The LocalDateTime can back constructed from epoch seconds as below
        //The result of the below code would back a LocalDateTime representing 2016-06-13T11:34:50
        LocalDateTime localDateTime2 = LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);
        System.out.println("localDateTime2: " + localDateTime2);
    }

    /**
     * the easy formatting of Date and Time
     */
    public static void formatting() {
        //Get the LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
        //标准格式化
        String localDateFormat = localDateTime.format(DateTimeFormatter.ISO_DATE);
        System.out.println("localDateFormat: " + localDateFormat);
        //格式化成yyyy/MM/dd
        String localDateFormat1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("localDateFormat1: " + localDateFormat1);
        //格式化成SIMPLIFIED_CHINESE
        String localDateFormat2 = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.CHINA));
        System.out.println("localDateFormat2: " + localDateFormat2);

    }
}
