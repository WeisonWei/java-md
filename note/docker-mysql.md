# Docker安装、运行MySql镜像
环境信息：WIN10专业版    
Docker:19.03.2    
![binaryTree](../../n-images/docker/docker-desktop.png "binaryTree")
## 操作步骤
1.拉取运行MySql      
```shell script
PS C:\Users\admin> docker pull mysql
Using default tag: latest
latest: Pulling from library/mysql
80369df48736: Pull complete 
e8f52315cb10: Pull complete 
cf2189b391fc: Pull complete 
cc98f645c682: Pull complete 
27a27ac83f74: Pull complete 
fa1f04453414: Pull complete 
d45bf7d22d33: Pull complete 
3dbac26e409c: Pull complete 
9017140fb8c1: Pull complete 
b76dda2673ae: Pull complete 
bea9eb46d12a: Pull complete 
e1f050a38d0f: Pull complete 
Digest: sha256:7345ce4ce6f0c1771d01fa333b8edb2c606ca59d385f69575f8e3e2ec6695eee
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest                            
```
2.后台运行一个名为`myDockerMysql`的mysql实例，使用Docker容器的3306端口映射我们本地3306端口，root密码为`123123`      
```shell script
PS C:\Users\admin> docker run --name myDockerMysql -e MYSQL_ROOT_PASSWORD=123123 -d -p 3306:3306 mysql    
ce10ad37915dde92f04c4e95fb08eca2a4169e8b86d5485ebe32b5952f1fbb49    
```

图示：     
![binaryTree](../../n-images/docker/docker-mysql-image.png "binaryTree")      
3.进入`myDockerMysql`的mysql实例的Docker容器，执行命令给root开启可远程登录       
```shell script  
#进入myDockerMysql容器 windows下老板powerShell执行要加winpty   
$ docker exec -it myDockerMysql bash
root@84ec54033d15:/#
#在容器内登陆Mysql
root@84ec54033d15:/# mysql -u root -p
#查看用户信息
root@84ec54033d15:/# select host,user,plugin,authentication_string from mysql.user;
#更新root连接信息
root@84ec54033d15:/# ALTER user 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123123';
root@84ec54033d15:/# FLUSH PRIVILEGES;
#查看用户信息
root@84ec54033d15:/# select host,user,plugin,authentication_string from mysql.user;
#退出
root@84ec54033d15:/# exit;
```

图示：       

![binaryTree](../../n-images/docker/docker-mysql-in.png "binaryTree")   
4.查看容器内MySQL运行状况      

可通过命令`docker ps`或者`Portainer工具`查看容器内MySQL运行情况：  
   
![binaryTree](../../n-images/docker/docker-mysql-portainer.png "binaryTree")   
5.使用客户端连接容器内运行的MySQL     

![binaryTree](../../n-images/docker/docker-mysql-connect1.png "binaryTree")   

随便找个表查一查：      

![binaryTree](../../n-images/docker/docker-mysql-connect2.png "binaryTree")   

通过虚拟出来的IP访问，连接不上：   

![binaryTree](../../n-images/docker/docker-mysql-connect3.png "binaryTree")   






















