docker-compose:用于dev,支持build restart,但是不支持deploy;
docker-swarm/docker-stack:用于prod,支持deploy的各种设置,包括分配cpu和内存, 但是创建容器只支持从image,不支持build;
