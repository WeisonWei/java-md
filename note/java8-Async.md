```
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<TaskLog> getFailedTaskLogs() {

        //1.COMPLETE的任务 超过3天
        CompletableFuture<Stream<TaskLog>> cf1 = taskLogRepository.findAllByStatus(TaskLog.STATUS.COMPLETE)
                .thenApply(this::makeTaskLogFailed);

        //2.ACTIVE的体验课三个任务
        //完成第一周体验课 起始周 周四结束
        CompletableFuture<Stream<TaskLog>> cf2 = taskLogRepository.findAllByStatusAndTag(TaskLog.STATUS.ACTIVE, COMPLETE_FIRST_WEEKLY_COURSE)
                .thenApply(this::makeFirstWeeklyCourseTaskLogFailed);

        //完成第二周体验课 起始第二周 周四结束
        CompletableFuture<Stream<TaskLog>> cf3 = taskLogRepository.findAllByStatusAndTag(TaskLog.STATUS.ACTIVE, COMPLETE_SECOND_WEEKLY_COURSE)
                .thenApply(this::makeSecondWeeklyCourseTaskLogFailed);

        //购买系统课 超过15天
        CompletableFuture<Stream<TaskLog>> cf4 = taskLogRepository.findAllByStatusAndTag(TaskLog.STATUS.ACTIVE, BUY_SYSTEM_COURSE)
                .thenApply(this::makeBuyExperienceCourseTaskLogFailed);

        CompletableFuture<Stream<TaskLog>> cf5 = cf1.thenCombine(cf2, Stream::concat)
                .thenCombine(cf3, Stream::concat)
                .thenCombine(cf4, Stream::concat);

        CompletableFuture.allOf(cf1, cf2, cf3, cf4, cf5).join();

        return cf5.join().collect(Collectors.toList());
    }
```