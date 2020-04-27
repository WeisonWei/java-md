## boot2docker v19.03.5
在使用Docker Machine时候，使用`virtualbox`类型的驱动，创建一个命名为test的Docker主机。
```bash
docker-machine create -d virtualbox swarm-work-node1
```
提示：
```bash
Running pre-create checks...
(swarm-manager) No default Boot2Docker ISO found locally, downloading the latest release...
(swarm-manager) Latest release for github.com/boot2docker/boot2docker is v19.03.5
(swarm-manager) Downloading /Users/admin/.docker/machine/cache/boot2docker.iso from https://github.com/boot2docker/boot2docker/releases/download/v19.03.5/boot2docker.iso
```

下载，百度网盘 --> 地址
下载后放到`/Users/admin/.docker/machine/cache/`就可以了；



>https://github.com/boot2docker/boot2docker/releases