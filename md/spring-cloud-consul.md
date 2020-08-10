➜  ~ consul version


三、启动

3.1 启动server端
基本的命令为：
consul agent -server -bind=10.0.xx.55 -client=0.0.0.0 -bootstrap-expect=3 -data-dir=/data/application/consul_data/ -node=server1

看着一大堆参数，其实不复杂
-server 表示是以服务端身份启动
-bind 表示绑定到哪个ip（有些服务器会绑定多块网卡，可以通过bind参数强制指定绑定的ip）
-client 指定客户端访问的ip(consul有丰富的api接口，这里的客户端指浏览器或调用方)，0.0.0.0表示不限客户端ip
-bootstrap-expect=3 表示server集群最低节点数为3，低于这个值将工作不正常(注：类似zookeeper一样，通常集群数为奇数，方便选举，consul采用的是raft算法)
-data-dir 表示指定数据的存放目录（该目录必须存在）
-node 表示节点在web ui中显示的名称
启动成功后，终端窗口不要关闭，可以在浏览器里，访问下，类似 http://10.0.xx.55:8500/，正常的话，应该会看到一行文字：Consul Agent。
为了防止终端关闭后，consul退出，可以在刚才命令上，加点东西，类似：
nohup xxx  > /dev/null 2>&1 & 

即:
nohup consul agent -server -bind=10.0.xx.55 -client=0.0.0.0 -bootstrap-expect=3 -data-dir=/data/application/consul_data/ -node=server1 > /dev/null 2>&1 &
将其转入后台运行。

另外2台节点上，也做类似操作：
nohup consul agent -server -bind=10.0.xx.203 -client=0.0.0.0 -bootstrap-expect=3 -data-dir=/data/application/consul_data/ -node=server2 > /dev/null 2>&1 &

注意更改bind参数的ip，以及node参数里的节点名称。
nohup consul agent -server -bind=10.0.xx.204 -client=0.0.0.0 -bootstrap-expect=3 -data-dir=/data/application/consul_data/ -node=server3 > /dev/null 2>&1 &

 

3.2 启动client端

几乎完全一样，只是把-server 去掉，在10.0.xx.205上运行：
1
	
nohup consul agent -client=0.0.0.0 -data-dir=/data/application/consul_data/ -node=client1  -ui  > /dev/null 2>&1 &