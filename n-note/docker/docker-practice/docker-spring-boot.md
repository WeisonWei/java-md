# SpringBoot web应用部署在Docker上运行
环境信息：   
OS ：Win10专业版   
Docker Desktop ：2.1.0.3   

前置条件：
1. 下载Docker Win10客户端 --> [链接](https://hub.docker.com/?overlay=onboarding)     
2. 开启Win10专业版Hyper-V   
3. 注册账户并登录   
图示1：   
![binaryTree](../../n-images/docker/dockerDesktop1.PNG "binaryTree")   
图示2：     
![binaryTree](../../n-images/docker/dockerDesktop2.PNG "binaryTree")
4. 安装portainer(这个是一个图形化工具，可选)   

## 1 新建SpringBoot WEB应用
### 1.1 pom.xml

``` java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.0.RELEASE</version>
        <relativePath/>
    </parent>
    <groupId>com.wxx</groupId>
    <artifactId>springboot-docker</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-docker</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <docker.registry>10.0.75.0:5000</docker.registry>
        <docker.image.prefix>springboot-docker</docker.image.prefix>
        <dockerfile-maven-version>1.4.13</dockerfile-maven-version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.4.9</version>
                <configuration>
                    <repository>${docker.image.prefix}/${project.artifactId}</repository>
                    <buildArgs>
                        <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

![binaryTree](../../n-images/springBoot-docker.PNG "binaryTree")    


### 1.2 Dockerfile
pom.xml同级目录下创建Dockerfile文件：

``` java 
FROM openjdk:8-jdk-alpine  # 基础镜像
VOLUME /tmp                # 容器访问宿主机上的目录
ARG JAR_FILE               # 引用参数
COPY ${JAR_FILE} app.jar   # 拷贝文件
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"] # 容器启动后启动应用
EXPOSE 8080                # 指定端口
```

## 2 构建镜像

执行maven命令进行打包：   
``` shell
mvn install  -- 打包web应用   
mvn dockerfile:build -- 打包docker镜像 
```   
  
或者执行组合的：` mvn clean install dockerfile:build` 图示：   
![binaryTree](../../n-images/springBoot-docker-package.PNG "binaryTree")    


## 3 启动镜像
打开`Git Bash` 或 `PowerShell`     
### 3.1 查看打包好的web应用镜像   

``` shell
docker images 
```
![binaryTree](../../n-images/springBoot-docker-image.PNG "binaryTree")    
### 3.2 启动容器
通过REPOSITORY启动：   

``` shell
docker run -p 8181:8181 -t  springboot-docker/springboot-docker 
```
![binaryTree](../../n-images/springBoot-docker-image-start.PNG "binaryTree")    

通过IMAGE ID启动：   

``` shell
docker run -p 8181:8181 -t 5ac1425a71de  
```

![binaryTree](../../n-images/springBoot-docker-image-start2.PNG "binaryTree")    


### 3.4 到portainer上查看状态   
![binaryTree](../../n-images/springBoot-docker-image-start1.PNG "binaryTree")    

## 4 调用接口   
用浏览器调用 `localhost:8181` --映射--> docker容器8181端口：   
![binaryTree](../../n-images/springBoot-docker-api.PNG "binaryTree")    


参考：   
[ 1 ] [https://spring.io/guides/gs/spring-boot-docker/](https://spring.io/guides/gs/spring-boot-docker/)
[ 2 ] [https://docs.docker.com/assemble/spring-boot/](https://docs.docker.com/assemble/spring-boot/)
