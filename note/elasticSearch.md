##安装
把安装包挪到`/usr/local`下
##配置
elasticsearch.yml
--> host默认：127.0.0.1 会以低性能运行
--> 开启head连接：
```java
http.cors.enabled: true
http.cors.allow-origin: "*"
```
##启动
执行/bin/elasticsearch*
##连接
es-head地址：http://localhost:9100/