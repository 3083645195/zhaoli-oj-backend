# zhaoli-oj-backend

本项目基于 Vue 3+Spring Boot + Spring Cloud 微服务 + Docker 的编程题目在线评测系统(简称 OJ)

## 后端技术栈

spring boot+mybatis+mybatis plus+redis+java-docker(后期使用spring cloud alibaba改造项目为微服务)

## 后端知识

- Java 进程控制
- Java 安全管理器
- 部分 JVM 知识点
- Docker（代码沙箱实现）
- Spring Cloud 微服务
- 消息队列
    - rabbitmq消息队列
- 多种设计模式
    - 简单工厂设计模式、模板方法设计模式、策略模式、代理模式

## 核心业务

判题服务:获取题目信息、预计的输入输出结果，返回给主业务后端:用户的答案是否正确

代码沙箱:只负责运行代码，给出结果，不管什么结果是正确的。**实现解耦**

### 流程图

![img_1](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/img_1.png)

### 时序图

![img_2](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/img_2.png)

## 实现核心

1. 权限校验

- 谁能提代码，谁不能提代码

2. 代码沙箱(安全沙箱)

- 用户代码藏毒:写个木马文件、修改系统权限
- 沙箱:隔离的、安全的环境，用户的代码不会影响到沙箱之外的系统的运行
- 资源分配:系统的内存就2个G，用户疯狂占用资源占满你的内存，其他人就用不了了。所以要限制用户程序的占用资源。

3. 判题规则

- 题目用例的比对，结果的验证

4. 任务调度

- 服务器资源有限，用户要排队，按照顺序去依次执行判题，而不是直接拒绝

## 功能

1. 用户模块

- 注册
- 登录

2. 题目模块

- 创建题目(管理员)
- 删除题目(管理员)
- 修改题目(管理员)
- 搜索题目(用户)
- 在线做题
- 提交题目代码

3. 判题模块

- 提交判题(结果是否正确与错误)
- 错误处理(内存溢出、安全性、超时)
- **自主实现** 代码沙箱(安全沙箱)
- 开放接口(提供一个独立的新服务)

## 判题机模块架构

> 目的:先跑通完整的业务流程，再进行代码沙箱复杂的实现

### 判题模块和代码沙箱的关系

- 判题模块:调用代码沙箱，把代码和输入交给代码沙箱去执行
- 代码沙箱:只负责接受代码和输入，返回编译运行的结果，不负责判题(
  可以作为独立的项目/服务，提供给其他的需要执行代码的项目去使用)

### 这两个模块完全解耦

![img_3](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/img_3.png)

### 代码沙箱需要接受和输出一组运行用例

前提:我们的每道题目有多组测试用例<br>
如果是每个用例单独调用一次代码沙箱，会调用多次接口、需要多次网络传输、程序要多次编译、记录程序的执行状态(
重复的代码不重复编译)<br>
这是一种很常见的性能优化方法!(批处理)

### 代码沙箱架构开发

1. 定义代码沙箱的接口，提高通用性

- 之后我们的项目代码只调用接口，不调用具体的实现类，这样在你使用其他的代码沙箱实现类时，就不用去修改名称了，便于扩展。

2. 定义多种不同的代码沙箱实现

- 示例代码沙箱:仅为了跑通业务流程
- 远程代码沙箱:实际调用接口的沙箱
- 第三方代码沙箱:调用网上现成的代码沙箱，https://github.com/criyle/go-judge

3. 编写单元测试，验证单个代码沙箱的执行

- 但是现在的问题是，我们把 new 某个沙箱的代码写死了，如果后面项目要改用其他沙箱，可能要改很多地方的代

4. 使用**工厂模式**，根据用户传入的字符串参数(沙箱类别)此处使用静态工厂模式，来生成对应的代码沙箱实现类

- 此处使用**静态工厂**模式，实现比较简单，符合我们的需求。
- 由此，我们可以根据字符串动态生成实例，提高了通用性。

5. 参数配置化，把项目中的一些可以交给用户去自定义的选项或字符串，写到配置文件中。这样开发者只需要改配置文件，而不需要去看你的项目代码，就能够自定义使用你项目的更多功能。
6. 代码沙箱能力增强

- 比如:我们需要在调用代码沙箱前，输出请求参数日志;在代码沙箱调用后，输出响应结果日志，便于管理员去分析。
- 每个代码沙箱类都写一遍log.info?难道每次调用代码沙箱前后都执行 log?<br>
  使用代理模式，提供一个 Proxy，来增强代码沙箱的能力(代理模式的作用就是增强能力)
- 原本:需要用户自己去调用多次。<br>
  ![img_4](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/img_4.png)
- 使用代理后:
  不仅不用改变原本的代码沙箱实现类，而且对调用者来说，调用方式几乎没有改变，也不需要在每个调用沙箱的地方去写统计代码。<br>
  ![img_5](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/img_5.png)
- 代理模式的实现原理
    1. 实现被代理的接口
    2. 通过构造函数接受一个被代理的接口实现类
    3. 调用被代理的接口实现类，在调用前后增加对应的操作

7. 实现示例的代码沙箱

## 判题服务开发

定义单独的judgeService 类，而不是把所有判题相关的代码写到 questionSubmitService 里，有利于后续的模块抽离、微服务改造。

### 判题服务业务流程

1. 传入题目的提交id，获取到对应的题目、提交信息(包含代码、编程语言等)
2. 如果题目提交状态不为等待中，就不用重复执行了
3. 更改判题(题目提交)的状态为“判题中”，防止重复执行，也能让用户即时看到状态
4. 调用沙箱，获取到执行结果
5. 根据沙箱的执行结果，设置题目的判题状态和信息

### 判断逻辑

1. 先判断沙箱执行的结果输出数量是否和预期输出数量相等
2. 依次判断每一项输出和预期输出是否相等
3. 判题题目的限制是否符合要求
4. 可能还有其他的异常情况

### 策略模式优化

我们的判题策略可能会有很多种，比如:我们的代码沙箱本身执行程序需要消耗时间，这个时间可能不同的编程语言是不同的，比如沙箱执行
Java 要额外花 3 秒。<br>
我们可以采用策略模式，针对不同的情况，定义独立的策略，便于分别修改策略和维护。而不是把所有的判题逻辑、if ... else .
代码全部混在一起写。<br>
实现步骤如下:

1. 定义判题策略接口，让代码更加通用化
2. 定义判题上下文对象，用于定义在策略中传递的参数(可以理解为一种 DTO)
3. 实现默认判题策略，先把 judgeService 中的代码搬运过来
4. 再新增一种判题策略，通过 if.. else. 的方式选择使用哪种策略<br>
   但是，如果选择某种判题策略的过程比较复杂，如果都写在调用判题服务的代码中，代码会越来越复杂，会有大量 if .. else
   .，所以建议单独编写一个判断策略的类。
5. 定义JudgeManager，目的是尽量简化对判题功能的调用，让调用方写最少的代码、调用最简单。对于判题策略的选取，也是在JudgeManager里处理的。





![swagger](https://zhaoli-image.oss-cn-beijing.aliyuncs.com/img/swagger.png)