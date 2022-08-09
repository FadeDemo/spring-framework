# http invoker

### 为什么出现http invoker

rmi使用java标准的对象序列化，但是很难穿透防火墙；hessian能很好的穿透防火墙进行工作，但是它私有的序列化协议

### 与rest有什么区别

与rmi一样，它也要通过序列化协议传递一个远程对象，而rest不需要

### 源码分析

###### 服务端创建

我们先看一下 `HttpInvokerServiceExporter` 的层次结构：

![http-invoker#1](resources/2022-08-09_21-30.png)

分析过几个spring提供的rpc集成方案的朋友应该不陌生了，就像其它方案一样， `HttpInvokerServiceExporter` 实现了 `InitializingBean` ，所以我们来看下它的 `afterPropertiesSet` 方法：

![http-invoker#2](resources/2022-08-09_21-36.png)

![http-invoker#3](resources/2022-08-09_21-37.png)

上面最后一张图蛮简单的，创建一个jdk aop代理

###### 客户端创建

`HttpInvokerProxyFactoryBean` 的层次结构为：

![http-invoker#4](resources/2022-08-09_21-43.png)

它实现了 `InitializingBean` 和 `FactoryBean` 接口

实现 `FactoryBean` 接口没什么特别的地方，就是返回一个代理对象：

![http-invoker#5](resources/2022-08-09_21-45.png)

那就还是来看它实现 `InitializingBean` 得来的 `afterPropertiesSet` 方法：

![http-invoker#6](resources/2022-08-09_21-49.png)

![http-invoker#7](resources/2022-08-09_21-49_1.png)

![http-invoker#8](resources/2022-08-09_21-50.png)

上面的方法执行途径主要创建了一个 `SimpleHttpInvokerRequestExecutor` 

回到前面：

![http-invoker#6](resources/2022-08-09_21-49.png)

剩下的代码就是把自身作为aop拦截器创建一个代理：

![http-invoker#9](resources/2022-08-09_21-55.png)

![http-invoker#10](resources/2022-08-09_21-55_1.png)

### 客户端调用远程对象方法

因为此时的远程对象是一个动态代理对象，所以它会执行到 `org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor.invoke` ：

![http-invoker#11](resources/2022-08-09_22-04.png)

