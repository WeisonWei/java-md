# dependencyManagement标签
## 1.作用
1.在Maven中dependencyManagement的作用其实相当于一个对所依赖jar包进行版本管理的管理器。


## 2.maven版本的声明方式
pom.xml文件中，jar的版本通过两种途径声明
1. dependencyManagement  
如果dependencies里的dependency自己没有声明version元素，那么maven就会到dependencyManagement里面去找有没有对该artifactId和groupId进行过版本声明，如果有，就继承它，如果没有就会报错，告诉你必须为dependency声明一个version

2. dependency  
如果dependencies中的dependency声明了version，那么无论dependencyManagement中有无对该jar的version声明，都以dependency里的version为准。


栗子🌰：
3.如下
```xml
<dependencyManagement>
   <dependencies>
     <dependency>
     <groupId>org.junit</groupId>
     <artifactId>junit-bom</artifactId>
     <version>5.5.2</version>
     <type>pom</type>
    <scope>import</scope>
 </dependency>
</dependencies>
</dependencyManagement>

<dependencies>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-launcher</artifactId>
    <version>1.5.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-commons</artifactId>
    <version>1.5.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-engine</artifactId>
    <version>1.5.2</version>
    <scope>test</scope>
</dependency>
</dependencies>
```
