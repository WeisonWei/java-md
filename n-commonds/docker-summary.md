# Docker 常用命令
-  1.Docker命令
- 查看docker容器版本
docker version
- 查看docker容器信息
docker info
- 查看docker容器帮助
docker --help

- # 1.1 容器生命周期管理
docker run
docker start/stop/restart
docker kill
docker rm
docker pause/unpause
docker create
docker exec
- # 1.2 容器操作
docker ps
docker inspect
docker top
docker attach
docker events
docker logs
docker wait
docker export
docker port

-- --- --  -  -- 
提示：对于容器的操作可使用CONTAINER ID 或 NAMES。
3.1、容器启动

- 新建并启动容器，参数：-i  以交互模式运行容器；-t  为容器重新分配一个伪输入终端；--name  为容器指定一个名称
docker run -i -t --name mycentos
- 后台启动容器，参数：-d  已守护方式启动容器
docker run -d mycentos

注意：此时使用"docker ps -a"会发现容器已经退出。这是docker的机制：要使Docker容器后台运行，就必须有一个前台进程。解决方案：将你要运行的程序以前台进程的形式运行。

- 启动一个或多个已经被停止的容器
docker start redis
- 重启容器
docker restart redis

3.2、容器进程

- top支持 ps 命令参数，格式：docker top [OPTIONS] CONTAINER [ps OPTIONS]
- 列出redis容器中运行进程
docker top redis
- 查看所有运行容器的进程信息
for i in  `docker ps |grep Up|awk '{print $1}'`;do echo \ &&docker top $i; done

3.3、容器日志

- 查看redis容器日志，默认参数
docker logs rabbitmq
- 查看redis容器日志，参数：-f  跟踪日志输出；-t   显示时间戳；--tail  仅列出最新N条容器日志；
docker logs -f -t --tail=20 redis
- 查看容器redis从2019年05月21日后的最新10条日志。
docker logs --since="2019-05-21" --tail=10 redis

3.4、容器的进入与退出

- 使用run方式在创建时进入
docker run -it centos /bin/bash
- 关闭容器并退出
exit
- 仅退出容器，不关闭
快捷键：Ctrl + P + Q
- 直接进入centos 容器启动命令的终端，不会启动新进程，多个attach连接共享容器屏幕，参数：--sig-proxy=false  确保CTRL-D或CTRL-C不会关闭容器
docker attach --sig-proxy=false centos 
- 在 centos 容器中打开新的交互模式终端，可以启动新进程，参数：-i  即使没有附加也保持STDIN 打开；-t  分配一个伪终端
docker exec -i -t  centos /bin/bash
- 以交互模式在容器中执行命令，结果返回到当前终端屏幕
docker exec -i -t centos ls -l /tmp
- 以分离模式在容器中执行命令，程序后台运行，结果不会反馈到当前终端
docker exec -d centos  touch cache.txt 

3.5、查看容器

- 查看正在运行的容器
docker ps
- 查看正在运行的容器的ID
docker ps -q
- 查看正在运行+历史运行过的容器
docker ps -a
- 显示运行容器总文件大小
docker ps -s


- 显示最近创建容器
docker ps -l
- 显示最近创建的3个容器
docker ps -n 3
- 不截断输出
docker ps --no-trunc 

- 获取镜像redis的元信息
docker inspect redis
- 获取正在运行的容器redis的 IP
docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' redis

3.6、容器的停止与删除

- 停止一个运行中的容器
docker stop redis
- 杀掉一个运行中的容器
docker kill redis
- 删除一个已停止的容器
docker rm redis
- 删除一个运行中的容器
docker rm -f redis
- 删除多个容器
docker rm -f $(docker ps -a -q)
docker ps -a -q | xargs docker rm
-  -l 移除容器间的网络连接，连接名为 db
docker rm -l db 
-  -v 删除容器，并删除容器挂载的数据卷
docker rm -v redis


3.8、容器与主机间的数据拷贝

- 将rabbitmq容器中的文件copy至本地路径
docker cp rabbitmq:/[container_path] [local_path]
- 将主机文件copy至rabbitmq容器
docker cp [local_path] rabbitmq:/[container_path]/
- 将主机文件copy至rabbitmq容器，目录重命名为[container_path]（注意与非重命名copy的区别）
docker cp [local_path] rabbitmq:/[container_path]
-- -- -- -- --- 




- # 1.3 容器rootfs命令
commit
cp
diff
镜像仓库
login
pull
push
search
- # 1.4 本地镜像管理


docker image pull是下载镜像的命令。镜像从远程镜像仓库服务的仓库中下载。
docker image pull alpine:latest命令会从 Docker Hub 的 alpine 仓库中拉取标签为 latest 的镜像。
docker image ls 列出了本地 Docker 主机上存储的镜像。可以通过 --digests 参数来查看镜像的 SHA256 签名。
docker image inspect 命令非常有用！该命令完美展示了镜像的细节，包括镜像层数据和元数据。
docker image rm 用于删除镜像。
docker image rm alpine:latest命令的含义是删除 alpine:latest 镜像。


- 列出本地images
docker images
- 含中间映像层
docker images -a

- 只显示镜像ID
docker images -q
- 含中间映像层
docker images -qa   

- 显示镜像摘要信息(DIGEST列)
docker images --digests
- 显示镜像完整信息
docker images --no-trunc


images
rmi
tag
build
history
save
load
import
- # 镜像搜索
- 搜索仓库MySQL镜像
docker search mysql
-  --filter=stars=600：只显示 starts>=600 的镜像
docker search --filter=stars=600 mysql
-  --no-trunc 显示镜像完整 DESCRIPTION 描述
docker search --no-trunc mysql
-  --automated ：只列出 AUTOMATED=OK 的镜像
docker search  --automated mysql
- # 1.5 info|version
info
version

- 下载Redis官方最新镜像，相当于：docker pull redis:latest
docker pull redis
- 下载仓库所有Redis镜像
docker pull -a redis
- 下载私人仓库镜像
docker pull bitnami/redis

2.4、镜像删除

- 单个镜像删除，相当于：docker rmi redis:latest
docker rmi redis
- 强制删除(针对基于镜像有运行的容器进程)
docker rmi -f redis
- 多个镜像删除，不同镜像间以空格间隔
docker rmi -f redis tomcat nginx
- 删除本地全部镜像
docker rmi -f $(docker images -q)



-  2.Docker Dockerfile 命令
2.5、镜像构建
- （1）编写dockerfile
cd /docker/dockerfile
vim mycentos
- （2）构建docker镜像
docker build -f /docker/dockerfile/mycentos -t mycentos:1.1

-  3.Docker Machine 命令
查看是否安装
docker-machine version
列出可用的机器
docker-machine ls
创建机器
docker-machine create --driver virtualbox test
查看机器的 ip
docker-machine ip test
停止机器
docker-machine stop test
启动机器
docker-machine start test
进入机器
docker-machine ssh test
-- -- 
查看当前激活状态的 Docker 主机
docker-machine active + 命令参数
- config：查看当前激活状态 Docker 主机的连接信息。
- creat：创建 Docker 主机
- env：显示连接到某个主机需要的环境变量
- inspect： 以 json 格式输出指定Docker的详细信息
- ip： 获取指定 Docker 主机的地址
- kill： 直接杀死指定的 Docker 主机
- ls： 列出所有的管理主机
- provision： 重新配置指定主机
- regenerate-certs： 为某个主机重新生成 TLS 信息
- restart： 重启指定的主机
- rm： 删除某台 Docker 主机，对应的虚拟机也会被删除
- ssh： 通过 SSH 连接到主机上，执行命令
- scp： 在 Docker 主机之间以及 Docker 主机和本地主机之间通过 scp 远程复制数据
- mount： 使用 SSHFS 从计算机装载或卸载目录
- start： 启动一个指定的 Docker 主机，如果对象是个虚拟机，该虚拟机将被启动
- status： 获取指定 Docker 主机的状态(包括：Running、Paused、Saved、Stopped、Stopping、Starting、Error)等
- stop： 停止一个指定的 Docker 主机
- upgrade： 将一个指定主机的 Docker 版本更新为最新
- url： 获取指定 Docker 主机的监听 URL
- version： 显示 Docker Machine 的版本或者主机 Docker 版本
- help： 显示帮助信息

-  4.Docker Compose 命令
docker-compose -f docker-compose.yml up -d
docker-compose stop
docker-compose kill
docker-compose ps
docker-compose rm
docker-compose restart
docker-compose down



常用命令
docker-compose up -d nginx                     构建建启动nignx容器    -- 以守护进程模式运行加-d选项
docker-compose exec nginx bash            登录到nginx容器中
docker-compose down                              删除所有nginx容器,镜像
docker-compose ps                         示所有容器
docker-compose restart nginx                   重新启动nginx容器
docker-compose run --no-deps --rm php-fpm php -v  在php-fpm中不启动关联容器，并容器执行php -v 执行完成后删除容器
docker-compose build nginx                     构建镜像 。        
docker-compose build --no-cache nginx   不带缓存的构建。
docker-compose logs  nginx                     查看nginx的日志 
docker-compose logs -f nginx                   查看nginx的实时日志
docker-compose config  -q                        验证（docker-compose.yml）文件配置，当配置正确时，不输出任何内容，当文件配置错误，输出错误信息。 
docker-compose events --json nginx       以json的形式输出nginx的docker日志
docker-compose pause nginx                 暂停nignx容器
docker-compose unpause nginx             恢复ningx容器
docker-compose rm nginx                       删除容器（删除前必须关闭容器）
docker-compose stop nginx                    停止nignx容器
docker-compose start nginx                    启动nignx容器


-  5.Docker Swarm 命令
docker swarm init 	用于创建一个新的 Swarm。执行该命令的节点会成为第一个管理节点，并且会切换到 Swarm 模式。
docker swarm join-token 	用于查询加入管理节点和工作节点到现有 Swarm 时所使用的命令和 Token。
要获取新增管理节点的命令，请执行 docker swarm join-token manager 命令；
要获取新增工作节点的命令，请执行 docker swarm join-token worker 命令。
docker node ls 	用于列出 Swarm 中的所有节点及相关信息，包括哪些是管理节点、哪个是主管理节点。
docker service create 	用于创建一个新服务。
docker service ls 	用于列出 Swarm 中运行的服务，以及诸如服务状态、服务副本等基本信息。
docker service ps <service> 	该命令会给出更多关于某个服务副本的信息
docker service inspect 	用于获取关于服务的详尽信息。附加 --pretty 参数可限制仅显示重要信息。
docker service scale 	用于对服务副本个数进行增减。
docker service update 	用于对运行中的服务的属性进行变更。
docker service logs 	用于查看服务的日志。
docker service rm 	用于从 Swarm 中删除某服务。该命令会在不做确认的情况下删除服务的所有副本，所以使用时应保持警惕。







> 通过docker-machine创建 swarm集群

1. 通过docker-machine创建manager和各从节点：
docker-machine create --driver virtualbox swarm-manager
docker-machine create --driver virtualbox swarm-worker-node1
docker-machine create --driver virtualbox swarm-worker-node2
docker-machine create --driver virtualbox swarm-worker-node3

2. 初始化管理节点master
docker-machine ssh swarm-manager
docker swarm init --advertise-addr 192.168.99.106

3. 添加工作节点worker
docker-machine ssh swarm-worker-node1
docker swarm join --token xxx 192.168.99.106:2377

4.Docker Swarm集群节点角色切换
4.1 管理器节点离开Docker Swarm集群
docker swarm leave --force
4.2 工作节点离开Docker Swarm集群
docker swarm leave
4.3 重新开启Docker Swarm集群
docker swarm init/join
4.4 Docker Swarm节点升级
docker node promote swarm-worker-node1

4.5 Docker Swarm节点降级
docker node demote swarm-manager
-  6.Docker Stack 命令
docker stsack deploy     用于根据 Stack 文件（通常是 docker-stack.yml）部署和更新 Stack 服务的命令。
docker stack ls  会列出 Swarm 集群中的全部 Stack，包括每个 Stack 拥有多少服务。
docker stack ps 列出某个已经部署的 Stack 相关详情。该命令支持 Stack 名称作为其主要参数，列举了服务副本在节点的分布情况，以及期望状态和当前状态。
docker stack rm 命令用于从 Swarm 集群中移除 Stack。移除操作执行前并不会进行二次确认。
-  7.Docker Network 命令
docker network connect 	将容器连接到网络。
docker network create 	创建新的 Docker 网络。默认情况下，在 Windows 上会采用 NAT 驱动，在 Linux 上会采用
Bridge 驱动。可以使用 -d 参数指定驱动（网络类型）。
docker network disconnect 	断开容器的网络。
docker network inspect 	提供 Docker 网络的详细配置信息。
docker network ls 	用于列出运行在本地 Docker 主机上的全部网络。
docker network prune 	删除 Docker 主机上全部未使用的网络。
docker network rm 	删除 Docker 主机上指定网络。


> https://www.cnblogs.com/DeepInThought/p/10896790.html