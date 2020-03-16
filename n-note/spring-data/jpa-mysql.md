

```java
@Async
    @Query(value = "select a from TaskLog as a, Task as b " +
            "where a.taskId = b.id and a.status = ?1 and a.updateDate = ?2 " +
            "and b.needFetch = true and b.isRepeatable = true order by a.id desc")
    CompletableFuture<List<TaskLog>> getAllRepeatableByStatusAndDate(TaskLog.STATUS status, String updateDate);
```