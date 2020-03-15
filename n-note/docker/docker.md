
# Docker 
Docker：单词意思为`码头`，是一个开源的高性能应用容器引擎；
应用场景：高效部署微服务等应用，简化应用的部署和运维；

官方解释：     
>一个容器不过是一个正在运行的进程，并对其应用了一些附加的封装功能，以使其与宿主主机和其他容器隔离；     
容器隔离的最重要方面之一是每个容器都与自己的私有文件系统进行交互，该文件系统由Docker镜像提供；   
镜像包括运行应用程序所需的所有内容：代码或二进制文件，运行时依赖项以及所需的任何其他文件系统对象。

------------------------------------

Docker和传统虚拟化对比高效的原因是：直接复用本地主机的操作系统，而传统方式则是在硬件层面实现：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200116173748285.png)
## 1.Docker知识体系
Docker跟生命周期有关的三个基本概念：
1. Image(镜像)
2. Container(容器)
3. Repository(仓库)

>镜像 = 由N层只读层组成，每层里存的可能是一个命令，也可能是一个依赖
>容器 = 镜像 + 读写层，是镜像的运行实例
>仓库 = 同Maven仓库，用来存储镜像，也分公有仓库和私有仓库

Docker核心组件：
1. Docker Client
2. Docker Daemon
3. Docker Image
4. Docker Container
5. Docker Registry

>Docker Client = Docker提供命令行(CLI)，用来执行各种命令
>Docker Daemon = Docker守护进程，响应来自Docker Client的请求，包含三部分：Server|Engine|Job
>**Image**|**Container**|**Registry**上边已解释

Docker官方编排项目(Orchestration)三剑客：
1. Docker Compose
2. Docker Machine
3. Docker Swarm

>Docker Compose = 单Docker Daemon的服务编排(单引擎多容器应用部署和管理)
>Docker Machine = 在各平台上快速安装Docker环境：Mac|Win|Linux，支持多种后端驱动：本机|虚拟|远程
>Docker Swarm = 多Docker Daemon的多服务编排(多引擎多容器应用部署和管理)，独有Service|Task概念，部署：单服务|多服务(Stack)

易混淆概念：Docker Compose & Docker Stack，因为Stack也使用Compose的`docker-compose.yml`编排服务；
弄清楚之前要先理解Docker Swarm的`Service`和`Task`：
1. Docker Compose
单个Daemon的服务编排，通过`docker-compose.yml`声明各个容器，yml中的`service属性`跟Swarm的`服务`不是一个概念；

2. Docker Stack
Docker Stack是Docker Swarm一部分，用来进行多个Docker Daemon的多服务编排，也使用`docker-compose.yml`声明；
它提供了简单的方式来部署应用并管理其完整的生命周期：初始化部署 -> 健康检查 -> 扩容 -> 更新 -> 回滚；
对比`Docker Compose`在yml中多了`deploy`和`secrets`等属性，用以声明和支持以上的容器`部署`和`管理`操作；
yml中的`image`属性不支持构建，需要提前打好镜像上传到仓库里；

>这是我目前的理解，如有偏差请指出
### 1.1 Docker知识点
Docker原生知识点梳理：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200117200809999.png)
### 1.2 Docker架构
Docker各核心组件的功能：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200116173912363.png)
### 1.3 Docker核心命令
Docker高频命令：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200116174754405.png)
### 1.4 容器生命周期
从`docker run 镜像`生成一个容器-->销毁的完整生命周期：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112210921128.png)
> 参考： [https://docs.docker.com/get-started/](https://docs.docker.com/get-started/)


## 2 镜像
### 2.1 镜像的理解
从`结构`上说：一个镜像是不可改变的、无状态的，是N个只读层（read-only layer）集合；   
从`内容`上说：一个镜像是由N个包含**命令**或者是**依赖**的只读层集合；

Docker通过**快照机制**来实现镜像层堆栈，对外将所有镜像层**堆叠并合并**，保证多镜像层对外展示为**统一**的文件系统：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112210604632.png)
通过命令：`docker image inspect 镜像名:tag`查看镜像分层情况，执行后返回一个包含各层信息的json串;
下图是通过`Portainer`可视化工具查看一个`SpringBoot Web应用`镜像的分层情况:     
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112210719236.png)
> 层可以理解为：制作镜像的步骤，每层可能是个命令，也可能是一个依赖的镜像；

### 2.2 镜像命令

| 命令 | 解释 |
| ------ | ------ |
|docker image build	|从Dockerfile构建镜像|
|docker image history	|显示镜像的历史记录|
|docker image import	|从tarball导入内容以创建文件系统镜像|
|docker image inspect	|在一个或多个镜像上显示详细信息|
|docker image load	|从tar存档或STDIN加载镜像|
|docker image |列出图片|
|docker image prune	|删除未使用的镜像|
|docker image pull	|从注册表中提取镜像或存储库|
|docker image push	|将镜像或存储库推送到注册表|
|docker image rm	 | 删除一个或多个镜像|
|docker image save	|将一个或多个镜像保存到tar存档（默认情况下流式传输到STDOUT）|
|docker image tag	|创建一个引用了SOURCE_IMAGE的标签TARGET_IMAGE|

### 2.3 栗子--拉取Ubuntu镜像

拉取`Ubuntu`镜像，下载过程中，会输出获取镜像的每一层信息：   

```shell script   
PS C:\Windows\System32> docker pull ubuntu   
Using default tag: latest   
latest: Pulling from library/ubuntu   
7ddbc47eeb70: Pull complete      
c1bbdc448b72: Pull complete   
8c3b70e39044: Pull complete        
45d437916d57: Pull complete      
Digest: sha256:6e9f67fa63b0323e9a1e587fd71c561ba48a034504fb804fd26fd8800039835d
Status: Downloaded newer image for ubuntu:latest
docker.io/library/ubuntu:latest
```

> 参考： [https://docs.docker.com/engine/reference/commandline/image/](https://docs.docker.com/engine/reference/commandline/image/)

## 3 容器

### 3.1 容器的理解

`容器`=`只读镜像1(基础镜像)`+`只读镜像2`+`只读镜像3`+`...`+`可读写顶层`

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112210751328.png)


### 3.2 容器命令
| 命令 | 解释 |
| ------ | ------ |
|docker container attach	   | 将本地标准输入，输出和错误流附加到正在运行的容器  |
|docker container commit	   |  根据容器的更改创建新镜像                         |
|docker container cp	       |  在容器和本地文件系统之间复制文件/文件夹          |
|docker container create	   |  创建一个新的容器                                 |
|docker container diff	   		|    检查容器文件系统上文件或目录的更改             |
|docker container exec	   		|    在正在运行的容器中运行命令                     |
|docker container export	   |    将容器的文件系统导出为tar存档                  |
|docker container inspect  		|   显示一个或多个容器的详细信息                   |
|docker container kill	   		|    杀死一个或多个正在运行的容器                   |
|docker container logs	   		|    提取容器的日志                                 |
|docker container ls	       |    列出容器                                       |
|docker container pause	   		|    暂停一个或多个容器中的所有进程                 |
|docker container port	   		|    列出端口映射或容器的特定映射                   |
|docker container prune	   		|    取出所有停止的容器                             |
|docker container rename	   |    重命名容器                                     |
|docker container restart  		|     重新启动一个或多个容器                         |
|docker container rm	       |    取出一个或多个容器                             |
|docker container run	   		|    在新容器中运行命令                             |
|docker container start	   		|    启动一个或多个已停止的容器                     |
|docker container stats	   		|    显示实时的容器资源使用情况统计流               |
|docker container stop	   		|    停止一个或多个运行中的容器                     |
|docker container top	   		|    显示容器的运行过程                             |
|docker container unpause  		|   取消暂停一个或多个容器中的所有进程             |
|docker container update	   |    更新一个或多个容器的配置                       |
|docker container wait	   		|    阻塞直到一个或多个容器停止，然后打印其退出代码 |

> 参考： [https://docs.docker.com/engine/reference/commandline/container/](https://docs.docker.com/engine/reference/commandline/container/)

### 3.3 栗子--构建Ubuntu容器
拉取`Ubuntu`镜像，下载过程中，会输出获取镜像的每一层信息：   
```shell script   
PS C:\Windows\System32> docker pull ubuntu
Using default tag: latest
latest: Pulling from library/ubuntu
7ddbc47eeb70: Pull complete  
c1bbdc448b72: Pull complete
8c3b70e39044: Pull complete  
45d437916d57: Pull complete  
Digest: sha256:6e9f67fa63b0323e9a1e587fd71c561ba48a034504fb804fd26fd8800039835d
Status: Downloaded newer image for ubuntu:latest
docker.io/library/ubuntu:latest
```


### 3.4 容器生命周期
Docker的核心就是容器，理解完整生命周期，有助于理解Dokcer的运行；
容器中的主进程就是容器本身，杀掉容器中的主进程，相当于杀掉了容器；
>生命周期图片在上边；

### 3.5 容器构建
在构建Docker容器时，应该尽量想办法获得体积更小的镜像，因为传输和部署体积较小的镜像速度更快；
但RUN语句总是会创建一个新层，而且在生成镜像之前还需要使用很多中间文件。 ；

`Dockerfile1`：
```shell script
FROM ubuntu
RUN apt-get update
RUN apt-get install vim
```
`Dockerfile2`：
```shell script
FROM ubuntu
RUN apt-get update && apt-get install vim
```
从Docker 1.10开始，COPY、ADD和RUN语句会向镜像中添加新层。`Dockerfile1`创建了3个层而 `Dockerfile2`创建了2个层。    
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112210955413.png)

减小Docker镜像体积的办法：     
1. 通过Docker多阶段构建将多个层压缩为一个      
2. 用distroless去除容器中所有不必要的东西      
3. 小体积的Alpine基础镜像      

构建层级：    
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112211026772.png)


## 4 Docker Compose
多数的现代应用通过多个更小的服务，可能是3个，也可能是10个互相协同来组成一个完整可用的应用；   
Docker Compose过一个声明式的配置文件描述整个应用，从而使用一条命令完成部署，应用部署后，还可以通过一系列简单的命令实现对其完整声明周期的管理。    

查看`docker-compose`版本：
```shell script
PS C:\Windows\System32> docker-compose --version
docker-compose version 1.24.1, build 4667896b
```

>Docker Compose默认使用文件名`docker-compose.yml`，使用`-f`参数指定文件，`-p `指定项目名，`-d` 后台运行；
>如果说 Dockerfile 是将容器内运行环境的搭建固化下来，那么Docker Compose可以理解为将多个容器运行的方式和配置固化下来；

### 4.1 Docker Compose使用
使用Docker Compose的步骤简化成三步：
1. 如果需要的话，编写容器所需镜像的 Dockerfile( 也可以使用现有的镜像 )；
2. 编写用于配置容器的docker-compose.yml；
3. 使用docker-compose命令启动应用。


### 4.2 Docker Compose配置
一个简单的`docker-compose.yml`：
```yaml
version: "3.5"
services:
  user-service:
    build: ""
    command: python app.py
    ports:
      - target: 5000
        published: 5000
    networks:
      - counter-net
    volumes:
      - type: volume
        source: counter-vol
        target: /code
  redis:
    image: "redis:alpine"
    networks:
      counter-net:

networks:
  counter-net:

volumes:
  counter-vol:
```

这个`docker-compose.yml`基本结构：
- version --> 必须且总是位于文件的第一行，它定义了Compose文件格式（主要是 API）的版本；
- services --> 定义不同的应用，上边定义了两个服务：一个名为`user-service`的Web服务和一个名为`redis`的内存数据库服务；
- networks --> 指引Docker创建新的网络，默认情况下创建bridge网络，这是单主机网络，只能够实现同一主机上容器的连接；
- volumes  --> 指引Docker来创建新的卷。

`services`部分定义了两个二级 key：`user-service`和`redis`;
它们各自定义了一个应用程序服务，Docker Compose 会将每个服务部署为一个容器，并且会使用`key`作为容器名字的一部分;

`user-service`的服务定义中，包含如下指令：   
1. build：指定Docker基于当前目录下Dockerfile中定义的指令来构建一个新镜像,该镜像被用于启动该服务的容器；
2. command：python app.py指定Docker在容器中执行名为 app.py 的 Python 脚本作为主程序；
3. ports：指定Docker将容器内（-target）的5000端口映射到主机（published）的5000端口；   
4. networks：使得Docker可以将服务连接到指定的网络上；
5. volumes：指定 Docker 将 source:counter-vol卷挂载到容器内的 target:/code,卷应该是已存在的，或是在下方的volumes中定义的。   

>综上，Docker Compose 会调用 Docker 来为 web-fe 服务部署一个独立的容器。
>该容器基于与 Compose 文件位于同一目录下的 Dockerfile 构建的镜像；
>基于该镜像启动的容器会运行 app.py 作为其主程序，将 5000 端口暴露给宿主机，连接到 counter-net 网络上，并挂载一个卷到/code；

## 5 Docker Swarm
Docker Swarm包含两部分内容：
1. 安全集群：集群管理(Manager和Worker节点)，Manager节点HA集群，密钥等；
2. 服务编排: 多Docker Daemon的单Service部署和多Service部署(Docker Stack)；

经常混淆的`compose`和`swarm/stack`是这样分工的:
>compose  常用于`dev`支持`build restart`但是不支持`deploy`；
>swarm/stack  用于`prod `支持 `deply`的各种设置，包括分配cpu和内存，但是创建容器只支持从`image`,不支持`build`；

### 5.1 Docker Swarm单Service部署
执行命令创建`服务`，`服务`会创建`task`根据策略(`replicated`|`global`)部署到各个节点：
```bash
docker service create --name uber-svc --network uber-net -p 80:80 --replicas 2 nigelpoulton/tu-demo:v1
```
1. 先`docker swarm init`初始化swarm;
2. 各个worker节点执行`docker swarm join token ...`加入到集群中;
3. 执行`docker service create --name Api服务 --network xxx -p 80:80 --replicas 2 image名:image版本`就会创建一个`2个任务(副本)`的`Api服务`


### 5.2 Docker Stack多Service部署
Docker Stack是构成特定环境中的`Swarm Service`集合, 它是自动部署多个相互关联的服务的简便方法，而无需单独定义每个服务；
通过提供期望状态、滚动升级、简单易用、扩缩容、健康检查等特性简化了应用的管理，这些功能都封装在一个声明式配置文件当中;
 
Stack File是一种`yaml`格式的文件，类似于`docker-compose.yml`文件，它定义一个或多个服务，以及服务的环境变量、部署标签、容器数量以及相关的环境特定配置等；
   
>Docker Compose适用于开发和测试，而Docker Stack则适用于大规模场景和生产环境；     
>Stack和Compose的声明文件属性基本一致，只是多了deploy属性

从体系结构上来讲，Stack位于Docker应用层级的最顶端；
Stack基于`Swarm服务`进行构建，而`服务`又基于`容器`，如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200112211114357.png)

Docker Stack部署最佳方法：
1. 先`docker-compose up --build`启动运行无误;
2. 执行`docker image ls` 查看创建出的镜名字,每个Service下面,增加deploy字段, 尤其是mode, 对单例, 设置为`global`;
3. 到`docker-compose.yml`所在目录下执行：`docker swarm init`
4. 执行stack命令进行部署：`docker stack deploy -c docker-compose.yml stack-name`

>[https://www.jianshu.com/p/25c529e3a3e6](https://www.jianshu.com/p/25c529e3a3e6)

## 6 应用容器化
`应用容器化`的核心思想就是如何将应用整合到容器中，并且能在容器中实际运行；    
完整的应用容器化过程主要分为以下几个步骤：    
1. 编写应用代码；
2. 创建一个 Dockerfile，其中包括当前应用的描述、依赖以及该如何运行这个应用；
3. 对该 Dockerfile 执行 docker image build 命令；
4. 等待 Docker 将应用程序构建到 Docker 镜像中；

 一旦应用容器化完成（即应用被打包为一个 Docker 镜像），就能以镜像的形式交付并以容器的方式运行了。    

## 7 服务编排
常见容器编排工具
1. Swarm
2. Fleet
3. Kubernetes
4. Mesos

Swarm是Docker原生服务编排工具，目前主流是Kubernetes；

### 7.1 Swarm
Swarm使用标准的Docker API，这意味着容器能够使用docker run命令启动，即使用Docker API的工具比如Compose和bespoke脚本也能使用Swarm，从而利用集群而不是在单个主机上运行。

### 7.2 Kubernetes
Kubernetes是一个由google基于他们上个世纪容器产品化的经验而推出的容器编排工具，Kubernetes有些固执己见对于容器如何组织和网络强制了一些概念；

要了解的主要概念有：
>1.Pods – Pods是容器一起部署与调度的群体，与其他系统的单一容器相比，它组成了Kubernetes中调度的原子单元；
>2.Flat Networking Space – Kubernetes的网络是跟默认的Docker网络不同；
>3.Labels – Labels是附在Kubernetes对象（主要是pods）上用于描述对象的识别特征的键值对，例如版本：开发与层级：前端；
>4.Services – Services是通过名称来定位的稳定的节点；
>5.Replication Controllers - Replication controllers是Kubernetes实例化pods的正常方式，它们为service来控制和监视运行的pods数量；

### 7.3 Fleet
Fleet是一个来自CoreOS的集群管理工具，自诩为“底层的集群引擎”，也就意味着它有望形成一个“基础层”的更高级别的解决方案，如Kubernetes。
Fleet最显著的特点是基于systemd（systemd提供单个机器的系统和服务初始化）建立的，Fleet将其扩展到集群上，Fleet能够读取systemd单元文件，然后调度单个机器或集群。
而CoreOS表示，Fleet项目目前已经不投入更多心力开发，仅仅处理安全性、漏洞修复等问题，而在2018年2月，CoreOS就会正式将Fleet从Container Linux中移除，不过使用者仍然可以取得它的容器映像档。

### 7.4 Mesos
Apache Mesos是一个开源的集群管理器。
它是为涉及数百或数千台主机的大规模集群而设计的。 
Mesos支持在多租户间分发工作负载，一个用户的Docker容器运行紧接着另一个用户的Hadoop任务。

Apache Mesos始于加州大学伯克利分校的一个项目，用来驱动Twitter的底层基础架构，并且成为许多大公司如eBay和Airbnb的重要工具。后来Mesosphere（共同创办人之一：Ben Hindman - Mesos原始开发人员 ）做了很多持续性的Mesos开发和支持工具（如Marathon）。



参考：   
[ 1 ] [https://docs.docker.com/get-started/](https://docs.docker.com/get-started/)    
[ 2 ] [http://dockone.io/article/783](http://dockone.io/article/783)    
[ 3 ] [https://docs.docker.com/get-started/resources/](https://docs.docker.com/get-started/resources/)    
[ 4 ] [http://docker-saigon.github.io/post/Docker-Internals](http://docker-saigon.github.io/post/Docker-Internals/#overview-of-container-runtimes:cb6baf67dddd3a71c07abfd705dc7d4b)    
[ 5 ] [http://c.biancheng.net/view/3143.html](http://c.biancheng.net/view/3143.html)    
[ 6 ] [https://docs.docker.com/engine/reference/builder/](https://docs.docker.com/engine/reference/builder/)    
[ 7 ] [https://docs.docker.com/compose/compose-file/](https://docs.docker.com/compose/compose-file/)    
[ 8 ] [https://selo77.github.io/2016/07/19/What-is-Docker/](https://selo77.github.io/2016/07/19/What-is-Docker/)    
[ 9 ] [https://www.jianshu.com/p/71b5511f4f43](https://www.jianshu.com/p/71b5511f4f43?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation)    

