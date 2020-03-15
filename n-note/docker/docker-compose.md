# Docker Compose
Docker Compose是以单引擎模式（Single-Engine Mode）进行多应用的部署和管理(单引擎模式的服务编排)；

解决的问题：一个完整应用的各协同服务的部署和管理，如一个完整网上商城后端应用的各个微服务：
解决的方式：通过一个声明式的YML配置文件描述整个应用，根据该文件配置执行命令完成部署；

## 1.Docker Compose 术语
Docker Compose运行目录下的所有文件（docker-compose.yml）组成一个工程,一个工程包含多个服务，
每个服务中定义了容器运行的镜像、参数、依赖，一个服务可包括多个容器实例；

Docker Compose将所管理的容器分为三层，分别是：
1. 工程（project）
2. 服务（service）
3. 容器（container）

## 2.Docker Compose使用步骤
Docker Compose使用的三个步骤：
1. 使用 Dockerfile 定义应用程序的环境；
2. 使用 docker-compose.yml 定义构成应用程序的服务，这样它们可以在隔离环境中一起运行；
3. 最后，执行 docker-compose up 命令来启动并运行整个应用程序；



## 3.docker-compose.yml
Docker Compose配置文件第一层级key包含：
1. version
2. services
3. networks
4. volumes

### 3.1 version
version是必须指定的，而且总是位于文件的第一行，它定义了Compose文件格式（主要是 API）的版本；
version和Docker引擎的版本号有一个对应关系：

|Compose file version|Docker引擎|
| ------ | ------ | 
1 | 1.9.0+
2.0 | 1.10.0+
2.1 | 1.12.0+
2.2, 3.0, 3.1, 3.2 | 1.13.0+
2.3, 3.3, 3.4, 3.5 | 17.06.0+
2.4|17.12.0+
3.6|18.02.0+
3.7|18.06.0+
使用的是哪个版本的Docker引擎,version就写对应的版本号；

>https://github.com/docker/compose/releases

### 3.2 services
services用于定义不同的应用服务：
```yaml
version: "3"
services:
  mysql:
    image: mysql:8.0.16
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MYSQL_USER=root
      - MYSQL_DATABASE=test
      - MYSQL_PASSWORD=root
    volumes:
      - mysql_data:/var/lib/mysql
      - ./mysql.ini:/etc/mysql/conf.d/my.cnf
      - ./init-mysql.sql:/docker-entrypoint-initdb.d/1-init-mysql.sql
      - ./init-master.sql:/docker-entrypoint-initdb.d/1-init-master.sql
    command: [
      "--log-bin=mysql-bin",
      "--binlog-format=ROW",
      "--server-id=1",
      "--sync_binlog=1"
    ]
    ports:
      - 3306:3306
    networks:
      - school-service-net
    ulimits:
      nproc: 65535
      nofile:
        soft: 20000
        hard: 40000
    restart: unless-stopped

  school-service-eureka:
    hostname: discovery
    image: localhost:5000/cloud-eureka:0.0.1-SNAPSHOT
    ports:
      - 8761:8761
    networks:
      - school-service-net
    restart: unless-stopped

  school-service-student:
    image: localhost:5000/school-service-student:0.0.1-SNAPSHOT
    ports:
      - 8080:8080
    networks:
      - school-service-net
    depends_on:
      - school-service-eureka
    restart: unless-stopped
    links:
      - school-service-eureka:discovery

  school-service-ribbon:
    image: localhost:5000/school-service-ribbon:0.0.1-SNAPSHOT
    ports:
      - 8082:8082
    networks:
      - school-service-net
    depends_on:
      - school-service-eureka
    restart: unless-stopped
    links:
      - school-service-eureka:discovery

volumes:
  mysql_data:

networks:
  school-service-net:

```

--------

具体的service下常用命令有(部分命令上述栗子未使用)：
1. build
```bash
build: .
```
指定Docker基于当前目录（.）下Dockerfile中定义的指令来构建一个新镜像，该镜像会被用于启动该服务的容器；
2. command
```bash
command python app.py 
```
指定 Docker 在容器中执行名为 app.py 的 Python 脚本作为主程序；
因此镜像中必须包含 app.py 文件以及 Python，这一点在 Dockerfile 中可以得到满足；
3. ports
```bash
ports:
   - 3306:3306
```
指定本容器内（-target）的3306端口映射到主机（published）的3306端口；
这意味着发送到Docker主机3306端口的流量会被转发到容器的3306端口；
容器中的应用监听端口 3306；
4. networks
```bash
networks:
  - school-service-net
```
使得本服务可以连接到指定的网络上，这个网络应该是已经存在的，或者是在一级key(networks)中定义的网络；
5. volumes
```bash
volumes:
  - mysql_data:/var/lib/mysql
```
指定Docker将`mysql_data`卷（source:）挂载到容器内的`/var/lib/mysql`（target:）；
`mysql_data`卷应该是已存在的，或者是在文件下方的一级key(volumes)中定义的；
6. image
```bash
image: localhost:5000/school-service-ribbon:0.0.1-SNAPSHOT
```
从Docker Hub或者Docker私服上拉取这个名为`localhost:5000/school-service-ribbon`，版本为`0.0.1-SNAPSHOT`的镜像
7. networks
```bash
networks:
   - school-service-net 
```
配置当前容器连接到名为`school-service-net`的网络；
8. hostname
```bash
hostname: discovery 
```
给当前容器指定主机名；

--------   

综上，Docker Compose会调用Docker来为`services`下各个声明的服务部署一个独立的容器；
该容器基于与Compose文件位于同一目录下的Dockerfile`构建`镜像或去Docker私服上`拉取`指定镜像；
各个容器运行启动后，将通过port声明的端口暴露给宿主机，连接到`school-service-net`网络上，
对于`mysql`这个服务还会挂载`mysql_data`卷和一些文件到宿主机的`/var/lib/mysql`等路径；

### 3.3 networks
networks用于指引Docker引擎创建新的网络；
默认情况下，Docker Compose会创建bridge类型的网络:
```yaml
networks:
  school-service-net:
  #driver: bridge 默认类型
```

### 3.4 volumes
volumes用于指引Docker引擎创建新的卷；
```yaml
volumes:
  mysql_data:
  mongo_data:
  rabbitmq_data:
  redis_data:
  elastic_data:
  postgres_data:
  activemq_data:
```

## 4.启动Docker Compose

1.编写一个完整可用的微服务`docker-compose.yml`：
```yaml
version: "3"
services:
  school-service-eureka:
    hostname: discovery
    image: localhost:5000/cloud-eureka:0.0.1-SNAPSHOT
    ports:
      - 8761:8761
    networks:
      - school-service-net
    restart: unless-stopped

  school-service-student:
    image: localhost:5000/school-service-student:0.0.1-SNAPSHOT
    ports:
      - 8080:8080
    networks:
      - school-service-net
    depends_on:
      - school-service-eureka
    restart: unless-stopped
    links:
      - school-service-eureka:discovery

  school-service-ribbon:
    image: localhost:5000/school-service-ribbon:0.0.1-SNAPSHOT
    ports:
      - 8082:8082
    networks:
      - school-service-net
    depends_on:
      - school-service-eureka
    restart: unless-stopped
    links:
      - school-service-eureka:discovery

networks:
  school-service-net:
```
>我本地已经通过maven插件编译发布了这三个镜像到本地Docker私服上

2.编写启动脚本`docker-compose.sh`：
```bash
#!/usr/bin/env bash
docker-compose -f docker-compose.yml up -d
```

3.执行启动脚本：
```bash
/usr/bin/env bash /Users/admin/gitCode/school-service/school-service/service-docker/docker-service-deploy/compose/docker-compose.sh
Creating network "compose_school-service-net" with the default driver
Creating compose_school-service-eureka_1 ... 
Creating compose_school-service-ribbon_1 ... 
Creating compose_school-service-student_1 ... 
Creating compose_school-service-student_1 ... done
```

