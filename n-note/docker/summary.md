Docker Daemon默认监听的是/var/run/docker.sock这个文件，所以docker客户端只要把请求发往这里，daemon就能收到并且做出响应
执行以下命令，可以直接发http请求到Docker Daemon，获取运行中的容器列表，等同于docker ps：
> curl -s --unix-socket /var/run/docker.sock http:/v1.39/containers/json

启动容器时的数据卷参数"/var/run/docker.sock:/var/run/docker.sock"有什么用？
宿主机的/var/run/docker.sock被映射到了容器内，有以下两个作用：

    在容器内只要向/var/run/docker.sock发送http请求就能和Docker Daemon通信了，官方提供的API文档中有详细说明，镜像列表、容器列表这些统统不在话下；
    如果容器内有docker文件，那么在容器内执行docker ps、docker port这些命令，和在宿主机上执行的效果是一样的，因为容器内和宿主机上的docker文件虽然不同，但是他们的请求发往的是同一个Docker Daemon；

基于以上结论，开篇问题中的docker启动脚本用到了/var/run/docker.sock参数，那么该容器应该会向Docker Daemon发送请求；

> https://docs.docker.com/engine/api/
