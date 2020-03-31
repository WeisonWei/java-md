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

```java
type --> OrderCompletedMessage

{
	"teacherId": "null",
	"couponUserId": "0",
	"order": {
		"id": "1816",
		"cid": "0",
		"mid": "0",
		"ctime": "1584521948555",
		"utime": "1584521957496",
		"del": "0",
		"uid": "431935847672320000",
		"stage": "3",
		"grouponId": "0",
		"invitePrizeId": "0",
		"outTradeNo": "xiong2020031816590898343",
		"type": "ALONE",
		"regtype": "RENEW",
		"packagesType": "SYSTEM_COURSE_YEAR",
		"sup": "DEFAULT",
		"packagesId": "4",
		"appleProductId": "system",
		"packagesName": "小熊美术年系统课",
		"topicId": "3",
		"addressId": "627",
		"couponUserId": "0",
		"packagesCourseWeek": "48",
		"packagesCourseDay": "0",
		"teacherId": "0",
		"payChannel": "8",
		"payChannelUser": "0",
		"payPageOrigin": "",
		"totalAmount": "0.04",
		"amount": "0.04",
		"discountAmount": "0.0",
		"gemIntegral": "0.0",
		"bearIntegral": "0.0",
		"endtime": "0",
		"buytime": "1584521957492",
		"status": "COMPLETED"
	},
	"pay": {
		"id": "1301",
		"cid": "0",
		"mid": "0",
		"ctime": "1584521949080",
		"utime": "1584521957475",
		"del": "0",
		"productId": "0",
		"packagesId": "4",
		"appleProductId": "",
		"uid": "431935847672320000",
		"oid": "1816",
		"title": "小熊美术年系统课",
		"type": "DEFAULT",
		"channel": "",
		"outTradeNo": "xiong2020031816590898343",
		"transactionId": "4200000508202003182932588922",
		"totalFee": "0.04",
		"tradeType": "APP",
		"outRefundNo": "",
		"refundId": "",
		"refundFee": "0.0",
		"refundType": "",
		"status": "SUCESS",
		"buytime": "1584521956000"
	},
	"sending": "true"
}
```




```java
{
        "name": "完成第一周体验课",
        "desc": "体验课开课后，4天内完成第一周体验课",
        "type": "COURSE",
        "tag": "COMPLETE_FIRST_WEEKLY_COURSE",
        "status": "ACTIVE",
        "amount": 1000,
        "isRepeatable": false,
        "needFetch": false,
        "courses": "0",
        "checkBuy": false
      }




      {
              "name": "完成第二周体验课",
              "desc": "体验课开课后，4天内完成第二周体验课",
              "type": "COURSE",
              "tag": "COMPLETE_SECOND_WEEKLY_COURSE",
              "status": "ACTIVE",
              "amount": 1500,
              "isRepeatable": false,
              "needFetch": false,
              "courses": "0",
              "checkBuy": false
            }


            {
                    "name": "购买系统课",
                    "desc": "体验课开课后15天内购买系统课",
                    "type": "COURSE",
                    "tag": "BUY_SYSTEM_COURSE",
                    "status": "ACTIVE",
                    "amount": 3000,
                    "isRepeatable": false,
                    "needFetch": false,
                    "courses": "1",
                    "checkBuy": false
                  }



                  {
                          "id": "1",
                          "cid": "0",
                          "mid": "0",
                          "ctime": "0",
                          "del": "0",
                          "name": "首次完成体验课",
                          "desc": "报名体验课，并首次完成1节体验课的学习，即可完成任务获得小熊币奖励。",
                          "type": "COURSE",
                          "tag": "LEARN_EXPERIENCE_COURSE",
                          "status": "ACTIVE",
                          "amount": 300,
                          "isRepeatable": false,
                          "needFetch": true,
                          "courses": "1",
                          "checkBuy": true,
                          "taskUrl": "artaiclass://art?type=open&page=mycourse",
                          "buyUrl": "https://www.xiaoxiongmeishu.com/h5/fortyNine/fortyNineExperience"
                        }                  





                        {
                                "id": "5",
                                "cid": "0",
                                "mid": "0",
                                "ctime": "0",
                                "del": "0",
                                "name": "每天首次分享课后报告",
                                "desc": "每天首次分享当日或过往课程的课后报告，即可完成任务获得小熊币奖励。",
                                "type": "COURSE",
                                "tag": "SHARE_TASK_REPORT",
                                "status": "ACTIVE",
                                "amount": 30,
                                "isRepeatable": true,
                                "needFetch": true,
                                "starValue": 1,
                                "courses": "0,1",
                                "checkBuy": true,
                                "taskUrl": "artaiclass://art?type=open&page=mycourse",
                                "buyUrl": "https://www.xiaoxiongmeishu.com/h5/fortyNine/fortyNineExperience"
                              }



                              
                              {
                                "id": "6",
                                "cid": "0",
                                "mid": "0",
                                "ctime": "0",
                                "del": "0",
                                "name": "首次绑定微信公众号",
                                "desc": "首次关注【小熊美术AI课】微信公众号，并点击自定义菜单，绑定学习手机号，即可完成任务获得小熊币奖励。",
                                "type": "BINDING_WEIXIN",
                                "tag": "BINDING",
                                "status": "ACTIVE",
                                "amount": 200,
                                "isRepeatable": false,
                                "needFetch": true,
                                "checkBuy": false
                              }

```

1.消息已经错过 L1无法记录 -- 查看完成当日课程用户 给补数据
2.400报错