### 简单介绍
随着网站功能的逐渐升级，对于日志的保存和异常的抓取显得尤为重要，SpringBoot基于微服务的框架搭建较为方便和解耦，这里用SpringBoot+ Aspect+RabbitMQ+MySQL+Hibinate+BootStrap+Mail搭建了一个基本的日志服务应用。

![](https://github.com/guangxush/iTechHeart/blob/master/image/Vinus/Vinus.png)

上图中，用户与应用程序进行交互可以产生大量的用户行为信息以及程序中抛出的异常信息，可以通过切面的形式获取，如果将大量的用户信息直接保存在数据库中，一方面由于数据量庞大的时候数据存储的性能受限，也有可能影响正常应用程序的功能，所以这里抓取日志信息之后将消息发给RabbitMQ,发送过程中可以对不同的日志进行分类处理；另一方面，日志的类别较多，可以是用户的行为也可以是程序的异常，后端不同的数据库集群可以订阅自己需要的消息主题，对日志进行持久化存储；存储之后的日志可以根据时间或者行为信息对其进行分类和聚类，以及可以增加一些AI算法对用户的兴趣进行提取，还有对于异常的抓取可以快速的定位到bug，及时报警做出处理等等。
### 具体框架

![](https://github.com/guangxush/iTechHeart/blob/master/image/Vinus/Vinus1.png)

具体实现框架如上图所示，这里编写了四个模块：
- Business模块 主要是模拟应用程序，这里简单的用注册登录表示，日志可以通过Aspect进行抓取和发送
- Mq模块 编写了向RabbitMQ中发送请求的Controller,供Business调用
- DB模块 应用程序自动监听RabbitMQ中所需要的消息，并根据不同的类型保存在不同的数据库表中
- Show模块对数据库表中的消息进行分析和处理，并将异常报警等等

### 源码下载
目前只是做了一个基本的Demo, 跟实际中的日志系统还有很大的差别，后续还会继续完善和规范代码。
[Vinus](https://github.com/guangxush/Vinus)
