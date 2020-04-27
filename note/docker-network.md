# Docker Network
Docker网络内容概览：
1. docker网络的组成 -- **沙盒** **网络** **端点**
2. docker容器之间的网络打通是通过 `--link` 和`expose`端口暴露实现
3. docker容器与宿主机之间的网络互联是根据端口映射实现

## 1.核心概念
在顶层设计中，Docker网络架构由3个主要部分构成：**CNM**、**Libnetwork**和**Driver**;
1. CNM(Container Network Model) -- CNM是设计标准，规定了容器网络架构的基础组成要素
2. Libnetwork -- CNM的具体实现，并且被Docker采用，由Go语言编写，实现了CNM中列举的核心组件
3. Driver -- 通过实现特定网络拓扑的方式来拓展该模型的能力

### 1.1 CNM
CNM是Docker网络架构模型，该方案是开源的并且支持插接式连接；
抽象的定义了3个基本要素：
- 沙盒(sandbox)是个独立的网络栈，其中包括以太网接口、端口、路由表以及DNS配置；
- 端点(endpoint)是虚拟网络接口，就像普通网络接口一样，端点主要职责是负责创建连接，以及将沙盒连接到网络；
- 网络(network)是802.1d网桥(类似大家熟知的交换机)的软件实现，网络就是需要交互的终端的集合；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120142225952.png)

### 1.2 Libnetwork
Libnetwork是Docker对CNM的实现，实现了网络架构的全部功能，还提供了`本地服务发现`和`基础的容器负载均衡`；
- 沙盒(Sandbox)
提供了容器的Endpoint套接字、IP 路由表、防火墙等，实现隔离了容器网络与宿主机网络，形成了完全独立的容器网络环境；
- 网络(Network)
Docker内部的虚拟子网，网络内的参与者相互可见并能够进行通讯，Docker的这种虚拟网络也是于宿主机网络存在隔离关系的，其目的主要是形成容器间的安全通讯环境；
- 端点(Endpoint)
端点是位于容器或网络隔离墙之上的洞，其主要目的是形成一个可以控制的突破封闭的网络环境的出入口；
当容器的端点与网络的端点形成配对后，就如同在这两者之间搭建了桥梁，便能够进行数据传输了；
Docker环境中最小的调度单位就是容器，CNM组件与容器进行关联的——沙盒被放置在容器内部，为容器提供网络连接；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120142338269.png)**服务发布**和**服务发现**是Docker Swarm使用的，在Swarm中进行总结；

> 项目地址：[https://github.com/docker/libnetwork](https://github.com/docker/libnetwork)

### 1.3 Driver
Docker封装了一系列本地驱动，不同的驱动可以通过插拔的方式接入Libnetwork来提供定制化的网络拓扑，实现开箱即用的效果；
Docker生态系统中的合作伙伴通过提供驱动的方式，进一步拓展了Docker的网络功能；
目前有两类，共五种驱动，Bridge和Overlay使用频率较高；

两类网络驱动：
1. 单机桥接网络(Single-Host Bridge Network)：**Bridge** | **Host** | **None**
2. 多机覆盖网络(Multi-Host Overlay)：**Overlay**

五种网络驱动：
1. Bridge Driver(**默认**) 桥接网络
2. Host Driver 主机网络
3. Overlay Driver 覆盖网络
4. MacVlan Driver mac虚拟局域网
5. None Driver 无网络

> 参考：[https://docs.docker.com/network/](https://docs.docker.com/network/) 

#### 1.3.1 Bridge
Bridge网络是Docker容器的默认网络驱动，通过网桥来实现网络通讯，网桥网络的实现可以基于硬件，也可以基于软件;
每个container通过docker0进行通信，docker0(虚拟交换机)是指装docker的时候默认创建的，所以默认创建的容器会绑到docker0;

#### 1.3.2 Host
host会删除容器与Docker主机之间的网络隔离，然后直接使用主机的网络；

#### 1.3.3 Overlay
overlay(覆盖网络)将多个Docker守护程序连接在一起，并使群集服务能够相互通信，实现跨主机容器间的通信；
还可以使用overlay(覆盖网络)来促进群集服务和独立容器之间或不同Docker守护程序上的两个独立容器之间的通信；

创建Overlay网络有些必要条件：
1. 使用集群才能创建 Overlay network
2. 开放需要的端口
    - TCP port 2377 for cluster management communications
    - TCP and UDP port 7946 for communication among nodes
    - UDP port 4789 for overlay network traffic

官网首推配合Swarm和Overlay一起使用，但Overlay不是最佳的解决方案；
Overlay网络是借助Docker集群模块Swarm来搭建的跨Docker Daemon网络，通过它搭建跨物理主机的虚拟网络，让不同物理机中运行的容器感知不到多个物理机的存在。

#### 1.3.4 Macvlan
Macvlan允许为容器分配MAC地址，使其在网络上显示为物理设备，Docker守护程序通过其MAC地址将流量路由到容器；
Macvlan在处理希望直接连接到物理网络而不是通过Docker主机的网络堆栈进行路由的旧应用程序时，使用本驱动是最佳选择；

#### 1.3.5 None
对于使用None的容器，禁用所有网络链接，通常与自定义网络驱动程序一起使用，None不适用于群体服务；



## 2.常用命令
最常用的就是创建，删除，查看了，创建时有很多参数可以使用；
使用`docker network --help`查看各参数解释；

### 2.1 docker network
`docker network --help`：
```bash
Usage:	docker network COMMAND
Manage networks
Commands:
  connect     Connect a container to a network
  create      Create a network
  disconnect  Disconnect a container from a network
  inspect     Display detailed information on one or more networks
  ls          List networks
  prune       Remove all unused networks
  rm          Remove one or more networks
```


### 2.2 docker network create
`docker network create --help`：
```bash
Usage:	docker network create [OPTIONS] NETWORK
Create a network
Options:
      --attachable           Enable manual container attachment
      --aux-address map      Auxiliary IPv4 or IPv6 addresses used by Network driver (default map[])
      --config-from string   The network from which copying the configuration
      --config-only          Create a configuration only network
  -d, --driver string        Driver to manage the Network (default "bridge")
      --gateway strings      IPv4 or IPv6 Gateway for the master subnet
      --ingress              Create swarm routing-mesh network
      --internal             Restrict external access to the network
      --ip-range strings     Allocate container ip from a sub-range
      --ipam-driver string   IP Address Management Driver (default "default")
      --ipam-opt map         Set IPAM driver specific options (default map[])
      --ipv6                 Enable IPv6 networking
      --label list           Set metadata on a network
  -o, --opt map              Set driver specific options (default map[])
      --scope string         Control the network's scope
      --subnet strings       Subnet in CIDR format that represents a network segment
```

## 3.网络特性
### 3.1 网络管理
查看并创建网络：
```bash
docker network ls #查看列表
docker network inspect #查看某网络详情
docker network create -d bridge local-bridge-net #创建bridge类型的网络
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120144601871.png)
### 3.2 容器互联
容器在启动时运行了一个本地DNS解析器，该解析器将请求转发到了Docker内部DNS服务器当中；
Docker DNS服务器中记录了容器启动时通过 `--name` 或者 `--net-alias `参数指定的`名称`与`容器`之间的映射关系；

运行2个**alpine**的**linux**容器 **os1**和**os2**，run时指定`接入网络`为`local-bridge-net`:
```bash
# 运行容器os2 
docker container run -d --name os1 --network local-bridge-net alpine sleep 1d

# 运行容器os2 并进入容器内
docker container run -it --name os2 --network local-bridge-net alpine sh
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120152719459.png)
在`os2`中去ping `os1`，发现时能ping通的；
这是因为`容器`和`地址`都注册到了指定的`Docker DNS`服务中，所以同一网络中的容器可以解析其他容器的名称；

在使用docker create 或 docker run 创建容器时可以通过`--link`选项指定要连接的`容器名字`就可以互联了；

```
$ sudo docker run -d --name mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes mysql
$ sudo docker run -d --name webapp --link mysql webapp:latest

```


### 3.3 端口暴露
容器间的网络打通了，但并不意味着我们可以任意访问被连接容器中的任何服务；
Docker为容器网络增加了一套安全机制，只有容器暴露的端口，才能被其他容器所访问；
执行`docker ps`的结果中可以看到容器暴露的端口：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120174638466.png)在容器创建时进行定义的方法是`--expose`这个选项：
```
# 创建运行`mysql3307`容器 暴露端口 3307 & 33070
sudo docker run -d --name mysql3307 -e MYSQL_RANDOM_ROOT_PASSWORD=yes --expose 3306 --expose 33070 mysql
```
像这样`3306/tcp, 33060/tcp`带斜杠的是容器间暴露端口，容器暴露端口类似打开了容器的防火墙，容器间就可以通过端口访问了：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120142314911.png)

### 3.4 端口映射
上边容器都是直接通过Docker网络互相访问的，还有一个常见的需求，就是需要在容器外通过网络访问容器中的应用；
如我们提供的Web服务，我们需要提供一种方式访问运行在容器中的Web应用；

Docker 中提供了一个`端口映射`功能实现这样的需求：
把`容器的端口`**映射到**`宿主操作系统的端口`上，当从外部访问宿主操作系统的端口时，数据请求就会自动发送给与之关联的容器端口；

要映射端口，我们可以在创建容器时使用`-p`或者是`--publish`选项：
```bash
# 格式是 `-p <ip>:<host-port>:<container-port>`
# ip是宿主操作系统的监听ip，可以用来控制监听的网卡，默认为 0.0.0.0，也就是监听所有网卡
# host-port 和 container-port 分别表示映射到宿主操作系统的端口和容器的端口
# 这两者是可以不一样，我们可以将容器的80端口映射到宿主操作系统的10086端口，传入 -p 10086:80 即可
docker container run -d --name nginx-web --network local-bridge-net --publish 10086:80 nginx
```

执行`docker ps`的结果中可以看到容器暴露的端口：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120170144408.png)nginx-web这个容器暴露的端口是80，端口的暴露可以通过Docker镜像进行定义，也可以在容器创建时进行定义；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120165904363.png)
对于`nginx-web`容器来说，它跟宿主主机的端口映射关系如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200120164935924.png)

> 参考：
> [https://docs.docker.com/network/](https://docs.docker.com/network/)
> [https://juejin.im/book/5b7ba116e51d4556f30b476c](https://juejin.im/book/5b7ba116e51d4556f30b476c)
> [http://c.biancheng.net/view/3185.html](http://c.biancheng.net/view/3185.html)
> [https://www.jianshu.com/p/2b398c51297a](https://www.jianshu.com/p/2b398c51297a)
