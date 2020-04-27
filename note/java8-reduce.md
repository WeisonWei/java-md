```java
String viceWeChatNo = map.get(false).stream()
                .map(UserInfo::getViceWechatNo)
                .reduce("", (viceWeChatNo1, viceWeChatNo2) -> viceWeChatNo1 + "|" + viceWeChatNo2);
```