# JUnit5
## 1 概述
JUnit4将所有内容捆绑到单个jar文件中；
Junit5由3个子项目组成，即JUnit Platform，JUnit Jupiter和JUnit Vintage，最低需要JDK8；

>JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Platform：是在JVM上启动测试框架的基础，简单地说这个包是用来调用测试用例的。
JUnit Jupiter：是JUnit5扩展的新的编程模型和扩展模型，提供了一堆测试要用的注解和类,用来编写测试用例。
JUnit Vintage：提供了一个在JUnit5平台上运行JUnit3和JUnit4的TestEngine 。

## 2 依赖
1. junit-jupiter-engine：具有运行时执行测试所必需的测试引擎实现
2. junit-jupiter-api：支持Junit注解（例如@Test）以编写测试和扩展
3. junit-jupiter-params：编写参数化测试所需要的依赖包
4. junit-vintage-engine：为在一个JUnit 4环境中执行了JUnit平台上测试和测试套件
5. junit-platform-launcher：为在一个JUnit 4环境中执行了JUnit平台上测试和测试套件
6. junit-platform-runner：供一个 Runner，允许在JUnit4环境中执行测试
7. junit-platform-commons：供一个junit platform公共组件



```java
        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            <maven.compiler.source>1.8</maven.compiler.source>
            <junit.jupiter.version>5.5.2</junit.jupiter.version>
            <junit.platform.version>1.5.2</junit.platform.version>
        </properties>


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

```
或
```java
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

           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter-api</artifactId>
               <scope>test</scope>
           </dependency>
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter-params</artifactId>
               <scope>test</scope>
           </dependency>
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter-engine</artifactId>
               <scope>test</scope>
           </dependency>
           <dependency>
               <groupId>org.junit.vintage</groupId>
               <artifactId>junit-vintage-engine</artifactId>
               <scope>test</scope>
           </dependency>
           <dependency>
               <groupId>org.junit.platform</groupId>
               <artifactId>junit-platform-launcher</artifactId>
               <scope>test</scope>
           </dependency>   
```
## 3 区别
JUnit5和JUnit4之间的注解差异：

|  特征 |  JUNIT4   | JUNIT5  |
|  ---- |  ----  | ----  |
| 声明一种测试方法	|  @Test | 	@Test |
| 在当前类中的所有测试方法之前执行	| @BeforeClass | 	@BeforeAll |
| 在当前类中的所有测试方法之后执行 | 	@AfterClass	| @AfterAll |
| 在每个测试方法之前执行 | 	@Before	| @BeforeEach |
| 每种测试方法后执行	| @After| 	@AfterEach |
| 禁用测试方法/类 | 	@Ignore	| @Disabled |
| 测试工厂进行动态测试| 	NA | 	@TestFactory |
| 嵌套测试| 	NA | 	@Nested |
| 标记和过滤	| @Category| 	@Tag |
| 注册自定义扩展	| NA | 	@ExtendWith |
## 4 使用栗子
