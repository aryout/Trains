## ThoughtWorks Homework


#### 背景介绍
The local commuter railroad services a number of towns in Kiwiland.

Because of monetary concerns, all of the tracks are 'one-way.'

That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.

In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!

The purpose of this problem is to help the railroad provide its customers with information about the routes.

In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.

更多的项目说明请参考 '.\陈道鹏_作业_Trains.pdf'

#### 安装说明

直接下载整个目录文件, 并且进入目录根目录, 请确保无二进制文件:
```java
> mvn clean
```
成功之后, 再进行编译:

```java
> mvn compile
```
也可以直接执行test命令来执行单元测试用例:
```java
> mvn test
```

#### 使用说明

单元测试执行完毕, 你可以通过运行以下两种方式来运行程序:

Windows CMD 下
```java
> mvn exec:exec -Dexec.executable="java" -Dexec.args=" -classpath %classpath com.faceyee.App [your-Graph-input-data-file-path]"
```
Windows PowerShell 下
```java
> mvn exec:exec '-Dexec.executable=java' '-Dexec.args= -classpath %classpath com.faceyee.App [your-Graph-input-data-file-path]'
```

如果开始时你省略了Graph测试文件路径参数，你将会看到以下的提示:

```
please type graph data path: 
```

此时, 请输入你本机中的测试文件地址.

在题目中的文档中有一套测试用例, 可以在项目根目录中找到;
```
> .\graph.txt
```
输出包含Graph构造语句和执行结果

```cmd
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
Output #6: 2
Output #7: 3
Output #8: 9
Output #9: 9
Output #10: 7
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.313 s
[INFO] Finished at: 2018-08-15T16:55:16+08:00
[INFO] Final Memory: 11M/155M
[INFO] ------------------------------------------------------------------------

```

#### 目录结构
```java
│  .gitignore
│  graph.txt                                        //  测试用例文件
│  pom.xml                                          // pom.xml 配置文件
│  README.md                                        //  说明文件
│  陈道鹏_作业_Trains.pdf                            //  原题说明
│  
└─src
    ├─main
    │  └─java
    │      └─com
    │          └─faceyee
    │              │  App.java                     //  程序入口文件
    │              │  
    │              ├─entity
    │              │      City.java                //  城市类
    │              │      Graph.java               //  地图类
    │              │      
    │              ├─service
    │              │      StraightRoad.java        //  深搜直达路径
    │              │      TrainRoad.java           //  路径处理结果
    │              │      
    │              └─utils
    │                      InputGraph.java          //  测试输入处理
    │                      
    └─test                                         //  Junit测试文件夹
        └─java
            └─com
                └─faceyee
                    │  AppTest.java                 //  入口参数单元测试
                    │  
                    ├─entity
                    │      CityTest.java            //  城市构造的单元测试
                    │      GraphTest.java           //  地图构造单元测试
                    │      
                    ├─service
                    │      StraightRoadTest.java     //  直达路径单元测试
                    │      TrainRoadTest.java        //  路径结果返回单元测试
                    │      
                    └─utils
                            InputGraphTest.java       //  测试文件输入验证单元测试
```

#### 作者
```java
@Author: 陈道鹏
@E-Mail: daopeng.chen@outlook.com
@QQ/Wechat: 973903350
@Telephone: 13438863920
```