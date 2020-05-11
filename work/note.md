## TODO-List

版本部署:
```bash
git push origin master:snapshot  
git push origin master:release
```

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
