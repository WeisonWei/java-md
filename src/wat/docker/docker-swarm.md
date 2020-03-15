# Docker Swarm 集群搭建
环境信息:
OS : MacOS Mojave
Docker : MAC
VirtualBox : 6.1.0-135406-OSX
Boot2docker : v19.03.5

在本地通过VirtualBox创建虚拟集群；
  
## 1. 创建节点
通过docker-machine创建master和各从节点：

```bash
docker-machine create --driver virtualbox swarm-master
docker-machine create --driver virtualbox swarm-work-node1
docker-machine create --driver virtualbox swarm-work-node2
docker-machine create --driver virtualbox swarm-work-node3
```
查看：
```bash
Weison:~ admin$ docker-machine ls
NAME               ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER     ERRORS
swarm-master       -        virtualbox   Running   tcp://192.168.99.102:2376           v19.03.5
swarm-work-node1   -        virtualbox   Running   tcp://192.168.99.100:2376           v19.03.5
swarm-work-node2   -        virtualbox   Running   tcp://192.168.99.103:2376           v19.03.5
swarm-work-node3   -        virtualbox   Running   tcp://192.168.99.104:2376           v19.03.5
Weison:~ admin$ docker-machine ip swarm-master
192.168.99.102
Weison:~ admin$ docker-machine ip swarm-work-node1
192.168.99.100
Weison:~ admin$ docker-machine ip swarm-work-node2
192.168.99.103
Weison:~ admin$ docker-machine ip swarm-work-node3
192.168.99.104
```

## 2. 初始化管理节点master
```bash
docker-machine ssh swarm-manager
docker swarm init --advertise-addr 192.168.99.106
```

## 3. 添加工作节点worker

```bash
docker-machine ssh swarm-worker-node1
docker swarm join --token SWMTKN-1-1dzzayh9tpzsrgjpsp2l018xlfzup02tc7pe6baviy25lsh2eq-dc147e1pkowyo52qu4iypbre3 192.168.99.106:2377
```

## 4.退出Docker Swarm集群模式

本地初始化了docker swarm，然后使用`docker-compose up`都会提示让使用`docker stack deploy`进行部署：

```bash
The Docker Engine you're using is running in swarm mode.
Compose does not use swarm mode to deploy services to multiple nodes in a swarm. All containers will be scheduled on the current node.
To deploy your application across the swarm, use `docker stack deploy`.
```

这个时候需要退出Docker Swarm模式：
1.管理器节点离开Docker Swarm模式

```bash
docker swarm leave --force
```

2.普通节点离开Docker Swarm模式

```bash
docker swarm leave
```

3.重新开启Docker Swarm模式

```bash
docker swarm init
```

4.Docker Swarm节点升级

```bash
docker node promote docker-desktop
```
5.Docker Swarm节点降级

```bash
docker node demote docker-desktop
```


```bash
docker swarm init --advertise-addr masterIp
```

## 5.创建Swarm Service
创建一个服务，并将其接入 uber-net 网络
```bash
docker service create --name uber-svc --network uber-net -p 80:80 --replicas 2 nigelpoulton/tu-demo:v1
```


以主机模式开放服务端口，需要较长格式的声明语法:
```bash
docker service  create --name uber-svc --network uber-net --publish published=80,target=80,mode=host --replicas 3 nigelpoulton/tu-demo:v1
```

滚动升级：一次更行2个，每20s执行一次：
```bash
docker service update --image nigelpoulton/tu-demo:v2 --update-parallelism 2 --update-delay 20s uber-svc
```

查看服务属性：
```bash
docker service inspect --pretty uber-svc
```


查看服务日志：
```bash
docker service logs uber-svc
```

## 6.stack
```bash
docker stack deploy -c docker-stack.yml seastack
```

1) 添加节点标签到 wrk-1   Node 标签只在 Swarm 集群之内生效。
docker@swarm-manager:~/stack/atsea-sample-shop-app$ docker node update --label-add pcidss=yes swarm-worker-node1
swarm-worker-node1
docker@swarm-manager:~/stack/atsea-sample-shop-app$ docker node inspect swarm-worker-node1
>参考：
>https://www.imooc.com/article/22651
>https://docs.docker.com/swarm/
>https://www.cnblogs.com/xiangsikai/p/9935814.html
>https://docs.docker.com/swarm/provision-with-machine/
