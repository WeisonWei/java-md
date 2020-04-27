```java
private List<UserTask> getCompleteUserTasks(Long userId) {
        List<TaskLog.STATUS> statuses = Stream.of(TaskLog.STATUS.COMPLETE, TaskLog.STATUS.PICKED, TaskLog.STATUS.FAILED)
                .collect(Collectors.toList());
        //获取COMPLETE PICKED FAILED
        CompletableFuture<List<UserTask>> allUserTasks = taskLogRepository.getAllUserTasks(userId, statuses);

        //上课中奖励
        CompletableFuture<List<UserTask>> userStarTasks = taskLogRepository.getUserStarTasks(userId, Task.Tag.STUDY_COURSE_STAR);

        //1.根据courseId合并计算总数 2.合并两个列表 allUserTasks和userStarTasks
        List<UserTask> completeUserTasks = userStarTasks.thenApply(this::mergeUserStarTasksByCourseId)
                .thenCombine(allUserTasks, this::addAllToList)
                .join();

        //根据Utime倒序排序并返回
        return sortUserTasksByUtimeDesc(completeUserTasks);
    }

private List<UserTask> mergeUserStarTasksByCourseId(List<UserTask> userTasks) {
        if (CollectionUtils.isEmpty(userTasks)) {
            return new ArrayList<>();
        }
        Map<String, List<UserTask>> map = userTasks.stream().collect(Collectors.groupingBy(UserTask::getDesc));
        Set<String> courseIds = map.keySet();
        Stream.Builder<UserTask> builder = Stream.builder();
        for (String courseId : courseIds) {
            List<UserTask> userTasksList = map.get(courseId);
            UserTask userTask = mergeUserStarTasks(userTasksList);
            if (userTask != null) {
                builder.accept(userTask);
            }
        }
        return builder.build().collect(Collectors.toList());
    }

   private List<UserTask> sortUserTasksByUtimeDesc(List<UserTask> list) {
        list.sort(Comparator.comparingLong(UserTask::getUtime).reversed());
        return list;
    }

```


```java
 private CompletableFuture<Float> sumStoneAmount(List<Account> accounts, Boolean greaterThanFourDay) {
        //对是否大于4天进行分区(2),分区条件: (当前时间-utime) >= 4天
        Map<Boolean, List<Account>> accountsMap = accounts
                .parallelStream()
                .collect(Collectors.partitioningBy(account -> (DateUtils.currentDateTime() - account.getUtime() >= Constant.FOUR_DAYS_MILLIS)));
        //计算未生效宝石(<4天)  计算已生效宝石(>=4天)
        return CompletableFuture.supplyAsync(() -> Objects.isNull(greaterThanFourDay) ? sumAmount(accounts) :
                sumAmount(accountsMap.get(greaterThanFourDay)));
    }
```

```java
@Transactional(readOnly = true)
    public Account getLastStoneAccount(long uid, Account.AccountType accountType) {
        accountService.initAccountIfNotExist(uid, accountType);
        //构造返回
        Account buildAccount = new Account().setUid(uid).setAccountType(STONE);
        // 对是STONE AccountList进行分区(1),分区条件: TransType是否为收入(INVITE,INIT)
        Map<Boolean, List<Account>> listMap = accountRepository.findAllByUidAndAccountType(uid, accountType)
                .parallel()
                .collect(Collectors.partitioningBy(account -> INVITE == account.getTransType() ||
                        Account.TransType.INIT == account.getTransType()));

        //计算未生效宝石(预计收入)
        CompletableFuture<Account> unEffectiveFuture = sumStoneAmount(listMap.get(true), false)
                .thenApply(buildAccount::setExpectedAmount);
        //计算已生效宝石
        CompletableFuture<Float> effectiveFuture = sumStoneAmount(listMap.get(true), true);
        //计算兑换总值
        CompletableFuture<Float> exchangeFuture = sumStoneAmount(listMap.get(false), null);

        //计算当前余额
        CompletableFuture<Account> balanceFuture = effectiveFuture
                .thenCombine(exchangeFuture, DataUtils::floatSubtract)
                .thenApply(buildAccount::setBalance);

        //获取最后一条记录的amount
        CompletableFuture<Account> amountFuture = latestAmountFuture(uid, accountType, buildAccount);

        CompletableFuture.allOf(unEffectiveFuture, balanceFuture, amountFuture).join();
        return buildAccount;
    }
```



```java

@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<TaskLog> getFailedTaskLogs() {

        //1.COMPLETE的任务 超过3天
        CompletableFuture<Stream<TaskLog>> cf1 = taskLogRepository.findAllByStatus(TaskLog.STATUS.COMPLETE)
                .thenApply(this::makeTaskLogFailed);

        //2.ACTIVE的体验课三个任务
        //完成第一周体验课 起始周 周四结束
        CompletableFuture<Stream<TaskLog>> cf2 = taskLogRepository.findAllByStatusAndTag(ACTIVE, COMPLETE_FIRST_WEEKLY_COURSE)
                .thenApply(this::makeFirstWeeklyCourseTaskLogFailed);

        //完成第二周体验课 第二周 周四结束
        CompletableFuture<Stream<TaskLog>> cf3 = taskLogRepository.findAllByStatusAndTag(ACTIVE, COMPLETE_SECOND_WEEKLY_COURSE)
                .thenApply(this::makeSecondWeeklyCourseTaskLogFailed);

        //购买系统课 超过15天
        CompletableFuture<Stream<TaskLog>> cf4 = taskLogRepository.findAllByStatusAndTag(ACTIVE, BUY_SYSTEM_COURSE)
                .thenApply(this::makeBuySystemCourseTaskLogFailed);

        CompletableFuture<Stream<TaskLog>> cf5 = cf1.thenCombine(cf2, Stream::concat)
                .thenCombine(cf3, Stream::concat)
                .thenCombine(cf4, Stream::concat);

        CompletableFuture.allOf(cf1, cf2, cf3, cf4, cf5).join();

        return cf5.join().collect(Collectors.toList());
    }
```