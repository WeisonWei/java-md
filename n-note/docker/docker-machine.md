```bash
docker-machine create --driver virtualbox test
```


```bash
docker-machine create --driver virtualbox --engine-registry-mirror http://192.168.80.222:5000 test
echo "ifconfig eth1 192.168.99.100 netmask 255.255.255.0 broadcast 192.168.99.255 up" | docker-machine ssh [test] sudo tee /var/lib/boot2docker/bootsync.sh > /dev/null

```

```bash
ifconfig eth0 192.168.80.223 netmask 255.255.240.0
ifconfig eth0 192.168.80.1/24
ifconfig eth0:1 192.168.1.3/16  指定子接口

```


```bash
$ docker-machine config <docker-machine name>
$ docker-machine env <docker-machine name>
$ docker-machine inspect <docer-machine name>
$ docker-machine ip <docker-machine name>
$ docker-machine kill <docker-machine name>
$ docker-machine provision <docker-machine name>
$ docker-machine regenerate-certs <docker-machine name>
$ docker-machine restart <docker-machine name>
$ docker-machine ssh <docker-machine name>
$ docker-machine start <docker-machine name>
$ docker-machine status <docker-machine name>
$ docker-machine stop <docker-machine name>
$ docker-machine upgrade <docker-machine name>
$ docker-machine url <docker-machine name>
```


>https://segmentfault.com/q/1010000011611066