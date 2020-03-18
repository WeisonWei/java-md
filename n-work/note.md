## TODO-List

版本部署:
```bash
git push origin master:snapshot  
git push origin master:release
```

### 2020-3-13
#### 1 构造测试数据 -- 测试用户购课 能通过定时任务触发 -- 待定
#### 2 验证失效定时任务
#### 3 验证任务完成方法
#### 4 验证任务初始化定时任务
#### 5 确认涉及模块是否部署 -- ok
#### 6 jpa批量插入
#### 7 任务奖励金额变化 -- ok


### 2020-3-16
#### 1 统计性接口
- 连续签到n周用户
- 各状态任务数量
- 用户完成任务数量排行榜
- - - - - - - - - - - 
- 用户各类型账户余额排行榜
- 邀请有奖 统计：被邀请人 获得佣金  





手动发消息：
admin/123123
测试环境mq:http://47.98.38.59:15672
开发mq:http://47.97.210.170:15672
```java


type = TeachingStudentTaskMessage

{
 "studentId":"431935847672320000", 
 "star":"null", 
 "courseId":"601", 
 "courseTitle":"《交通秩序》", 
 "messageType":"COMPLETE_COURSE", 
 "courseDate":"2020-03-12", 
 "lessonType":"TRIAL", 
 "lesson":"S1L1U2Lesson2"
}
```