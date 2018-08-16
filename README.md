## ThoughtWorks Homework


#### 项目介绍
```
The local commuter railroad services a number of towns in Kiwiland.

Because of monetary concerns, all of the tracks are 'one-way.'

That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.

In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!

The purpose of this problem is to help the railroad provide its customers with information about the routes.

In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.

更多的项目说明请参考 '.\陈道鹏_作业_Trains.pdf'
```
Train是一个基于Java语言开发的，由Maven构建的项目，所以要运行该项目，需要依赖JRE和Maven，请保证您机器上已经运行它们。
#### 构建说明

解压缩项目压缩文件, 进入项目根目录（pom.xml所在目录）, 确保无二进制文件:
```java
> mvn clean
```
成功之后, 再进行编译:

```java
> mvn compile
```
也可以直接执行test命令执行单元测试用例:
```java
> mvn test
```
输出如下:

请注意，由于单元测试的执行顺序随机，所以可能出现Output乱序，和预期结果顺序不一致。可以在下节'运行'时，比对他们的顺序会发现符合。
```cmd
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.faceyee.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.109 s - in com.faceyee.AppTest
[INFO] Running com.faceyee.entity.CityTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in com.faceyee.entity.CityTest
[INFO] Running com.faceyee.entity.GraphTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in com.faceyee.entity.GraphTest
[INFO] Running com.faceyee.service.StraightRoadTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in com.faceyee.service.StraightRoadTest
[INFO] Running com.faceyee.service.TrainRoadTest
Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
Output #10: 7
Output #8: 9
Output #9: 9
Output #6: 2
Output #7: 3
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.005 s - in com.faceyee.service.TrainRoadTest
[INFO] Running com.faceyee.utils.InputGraphTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in com.faceyee.utils.InputGraphTest
[INFO] Running com.faceyee.utils.RexTextTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in com.faceyee.utils.RexTextTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.965 s
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

此时, 请输入你本机中的测试文件地址.测试文件内容形如：

```
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE6
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE5
Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE4
```
题目中含有一套测试用例, 可以在项目根目录中找到;
```
> .\graph.txt
```
此时输出包含Graph构造语句和执行结果

```cmd
please type graph data path:
.\graph.txt
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
[INFO] Total time: 14.436 s
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
    │                      InputGraph.java          //  测试数据输入处理
    │                      RexText.java             //  正则返回城市等
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
                            RexTextTest.java          //  测试正则返回单元测试
```

* **实体层**：根据输入的城市加权路径图，构建城市节点和地图模型，城市和地图是组合模式。
* **业务层**：地图缓存自身区域的所有城市环路，两点间的所有直达线路按需生成。每个路径处理函数使用同一个地图对象。
* **工具层**：地图输入处理，每一行代表一个地图对象；正则匹配处理，返回城市和参数。
#### 联系人
```java
@Author: 陈道鹏
@E-Mail: daopeng.chen@outlook.com
@QQ/Wechat: 973903350
@Telephone: 13438863920
```