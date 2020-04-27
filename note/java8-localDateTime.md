

```java
public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        long milli = today.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long todayMill = getTodayMill();
        System.out.println(todayMill);
    }
```
