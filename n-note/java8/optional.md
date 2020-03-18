flatMap 可用于解决：将Optional<Optional<T>> ot --> Optional<T>
通过将Optional<T> oa--> 查询返回一个 <Optional<T>> ob
oa.flatmap(a-> func(a))

```java
private Optional<TaskLog> buildTaskLogFromUserIdWhenNotExist(Task task, Long userId, TaskLog.STATUS status) {
        //orderService: userId --> 期号 体验课ID=5
        //managementService: 期号 --> 时间
        String courseDay = orderService.getOrdersByUidAndPackagesId(userId, 5, "COMPLETED")
                .getPayload()
                .stream()
                .findAny()
                .map(Order::getStage)
                .map(period -> managementService.getManagementsByPeriods(period.toString(), TESTCOURSE).getPayload())
                .map(Collection::stream)
                .flatMap(Stream::findAny)
                .map(Management::getCourseDay)
                .map(DateUtils::formattedDate)
                .orElse(null);

        Optional<TaskLog> optionalTaskLog = Optional.of(task.buildTaskLog(userId).setUpdateDate(courseDay).setStatus(status));
        return Objects.isNull(courseDay) ? Optional.empty() : optionalTaskLog;

    }
```