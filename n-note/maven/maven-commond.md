# maven 命令
> maven 命令的格式为 mvn [plugin-name]:[goal-name]

## mvn命令参数

可以接受的参数如下：

    mvn -v, --version 显示版本信息;
    mvn -V, --show-version 显示版本信息后继续执行Maven其他目标;
    mvn -h, --help 显示帮助信息;
    mvn -e, --errors 控制Maven的日志级别,产生执行错误相关消息;
    mvn -X, --debug 控制Maven的日志级别,产生执行调试信息;
    mvn -q, --quiet 控制Maven的日志级别,仅仅显示错误;
    mvn -Pxxx 激活 id 为 xxx的profile (如有多个，用逗号隔开);
    mvn -Dxxx=yyy 指定java全局属性;
    mvn -o , --offline 运行offline模式,不联网更新依赖;
    mvn -N, --non-recursive 仅在当前项目模块执行命令,不构建子模块;
    mvn -pl, --module_name 在指定模块上执行命令;
    mvn -ff, --fail-fast 遇到构建失败就直接退出;
    mvn -fn, --fail-never 无论项目结果如何,构建从不失败;
    mvn -fae, --fail-at-end 仅影响构建结果,允许不受影响的构建继续;
    mvn -C, --strict-checksums 如果校验码不匹配的话,构建失败;
    mvn -c, --lax-checksums 如果校验码不匹配的话,产生告警;
    mvn -U 强制更新snapshot类型的插件或依赖库(否则maven一天只会更新一次snapshot依赖);
    mvn -npu, --no-plugin-updates 对任何相关的注册插件,不进行最新检查(使用该选项使Maven表现出稳定行为，该稳定行为基于本地仓库当前可用的所有插件版本);
    mvn -cpu, --check-plugin-updates 对任何相关的注册插件,强制进行最新检查(即使项目POM里明确规定了Maven插件版本,还是会强制更新);
    mvn -up, --update-plugins [mvn -cpu]的同义词;
    mvn -B, --batch-mode 在非交互（批处理）模式下运行(该模式下,当Mven需要输入时,它不会停下来接受用户的输入,而是使用合理的默认值);
    mvn -f, --file <file> 强制使用备用的POM文件;
    mvn -s, --settings <arg> 用户配置文件的备用路径;
    mvn -gs, --global-settings <file> 全局配置文件的备用路径;
    mvn -emp, --encrypt-master-password <password> 加密主安全密码,存储到Maven settings文件里;
    mvn -ep, --encrypt-password <password> 加密服务器密码,存储到Maven settings文件里;
    mvn -npr, --no-plugin-registry 对插件版本不使用~/.m2/plugin-registry.xml(插件注册表)里的配置;

mvn常用命令

    mvn clean：清理
    mvn compile：编译主程序
    mvn test-compile：编译测试程序m
    vn test：执行测试
    mvn package：打包
    mvn install：安装

    创建maven项目：mvn archetype:create
    指定 group： -DgroupId=packageName
    指定 artifact：-DartifactId=projectName

    创建web项目：-DarchetypeArtifactId=maven-archetype-webapp
    创建maven项目：mvn archetype:generate
    验证项目是否正确：mvn validate

    maven 打包：mvn package
    只打jar包：mvn jar:jar
    生成源码jar包：mvn source:jar

    产生应用需要的任何额外的源代码：mvn generate-sources
    编译源代码： mvn compile
    编译测试代码：mvn test-compile
    运行测试：mvn test
    运行检查：mvn verify
    清理maven项目：mvn clean


    生成idea项目：mvn idea:idea
    安装项目到本地仓库：mvn install
    发布项目到远程仓库：mvn:deploy

    在集成测试可以运行的环境中处理和发布包：mvn integration-test
    显示maven依赖树：mvn dependency:tree
    显示maven依赖列表：mvn dependency:list
    下载依赖包的源码：mvn dependency:sources

    安装本地jar到本地仓库：mvn install:install-file -DgroupId=packageName -DartifactId=projectName -Dversion=version -Dpackaging=jar -Dfile=path

    跳过测试:-Dmaven.test.skip=true






-- --  --- - - -  -


常用maven命令

    创建maven项目：mvn archetype:create 
    指定 group： -DgroupId=packageName
    指定 artifact：-DartifactId=projectName
    创建web项目：-DarchetypeArtifactId=maven-archetype-webapp
    创建maven项目：mvn archetype:generate
    验证项目是否正确：mvn validatemaven 
    打包：mvn package只打jar包：mvn jar:jar
    生成源码jar包：mvn source:jar
    产生应用需要的任何额外的源代码：mvn generate-sources
    编译源代码： mvn compile
    编译测试代码：mvn test-compile
    运行测试：mvn test
    运行检查：mvn verify
    清理maven项目：mvn clean
    生成eclipse项目：mvn eclipse:eclipse
    清理eclipse配置：mvn eclipse:clean
    生成idea项目：mvn idea:idea
    安装项目到本地仓库：mvn install
    发布项目到远程仓库：mvn:deploy
    在集成测试可以运行的环境中处理和发布包：mvn integration-test
    显示maven依赖树：mvn dependency:tree
    显示maven依赖列表：mvn dependency:list
    下载依赖包的源码：mvn dependency:sources
    安装本地jar到本地仓库：mvn install:install-file -DgroupId=packageName -DartifactId=projectName -Dversion=version -Dpackaging=jar -Dfile=path

web项目相关命令

    启动tomcat：mvn tomcat:run
    启动jetty：mvn jetty:run
    运行打包部署：mvn tomcat:deploy
    撤销部署：mvn tomcat:undeploy
    启动web应用：mvn tomcat:start
    停止web应用：mvn tomcat:stop
    重新部署：mvn tomcat:redeploy
    部署展开的war文件：mvn war:exploded tomcat:exploded
    





