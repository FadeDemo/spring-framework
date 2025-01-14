# rmi

### jdk rmi

###### 参考资料

[RMI 系列（02）源码分析](https://www.cnblogs.com/binarylei/p/12115986.html)

[源码层面梳理Java RMI交互流程](https://tttang.com/archive/1530/#toc_0x00-rmi)

###### 简单例子

远程对象以接口和实现类的形式存在

并且接口要继承 `java.rmi.Remote` 接口，实现类要继承 `java.rmi.server.UnicastRemoteObject` (不一定)

```groovy
package org.fade.demo.springframework.rmi

import java.rmi.Remote
import java.rmi.RemoteException

/**
 * <p>必须实现 {@link Remote} 接口
 * */
interface JdkRmiService extends Remote {

	/**
	 * <p>必须抛出 {@link RemoteException}
	 * */
	int add(int a, int b) throws RemoteException

}
```

```groovy
package org.fade.demo.springframework.rmi

import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject

class JdkRmiServiceImpl extends UnicastRemoteObject implements JdkRmiService {

	@Override
	int add(int a, int b) throws RemoteException {
		a + b
	}

}
```

服务端

```groovy
package org.fade.demo.springframework.rmi

import java.rmi.registry.LocateRegistry

class JdkRmiServer {

	static void main(String[] args) {
		def service = new JdkRmiServiceImpl()
		def registry = LocateRegistry.createRegistry(9999)
		registry.bind("add", service)
	}

}
```

客户端

```groovy
package org.fade.demo.springframework.rmi

import java.rmi.Naming

class JdkRmiClient {

	static void main(String[] args) {
		def service = Naming.lookup("rmi://127.0.0.1:9999/add")
		println service.add(1, 2)
	}

}
```

###### 源码分析

1. 创建服务(远程)对象

因为服务对象继承了 `UnicastRemoteObject` 类，所以它在实例化的时候会去调用 `UnicastRemoteObject` 的构造方法：

![rmi#1](resources/2022-07-17_11-10.png)

![rmi#2](resources/2022-07-17_11-12.png)

![rmi#3](resources/2022-07-17_11-13.png)

![rmi#4](resources/2022-07-17_11-17.png)

`UnicastRemoteObject` 的构造方法一路执行到上面所示的图时，有几个关键的地方需要说明一下：

标号1主要是创建存根（stub）

标号2当存根是 `RemoteStub` 类型实例时，创建骨架（skeleton）

标号3启动其它线程进行网络通信并注册到 `ObjectTable` 中

标号4 维持了一个map便于获取要调用的方法

我们先来看看标号1：

![rmi#5](resources/2022-07-17_11-26.png)

这个if是判断是否存在是否存在以当前类名为前缀， `_Stub` 为后缀的类：

![rmi#6](resources/2022-07-17_11-29.png)

![rmi#7](resources/2022-07-17_11-32.png)

因为此时我们自定义的服务对象是不存在的，所以它最后执行的逻辑是：

![rmi#8](resources/2022-07-17_11-34.png)

这里的逻辑应该就挺熟悉的了，创建jdk动态代理， `InvocationHandler` 是 `RemoteObjectInvocationHandler` 类型的

因为前面标号1中我们创建的stub不是 `RemoteStub` 类型的，所以标号2这里我们不叙述

来看一下标号3：

![rmi#9](resources/2022-07-17_11-37.png)

![rmi#10](resources/2022-07-17_11-38.png)

![rmi#11](resources/2022-07-17_11-40.png)

`this.listen()` 启动其它线程进行网络通信：

![rmi#12](resources/2022-07-17_11-43.png)

注意这里启动了一个线程，它的 `Runnable` 是 `AcceptLoop` ，所以我们看一下 `AcceptLoop` 的run方法：

![rmi#15](resources/2022-07-17_11-52.png)

![rmi#16](resources/2022-07-17_11-54.png)

可以看到上图有一个 `this.serverSocket.accept()` ，这说明当前线程阻塞在这里等待socket的连接

回到前面， `super.exportObject(target)` 向 `ObjectTable` 注册自己（其实就是往几个map中存放一下）：

![rmi#13](resources/2022-07-17_11-46.png)

![rmi#14](resources/2022-07-17_11-47.png)

2. 创建注册中心（Registry）

![rmi#17](resources/2022-07-17_12-01.png)

![rmi#18](resources/2022-07-17_12-02.png)

![rmi#19](resources/2022-07-17_12-03.png)

![rmi#4](resources/2022-07-17_11-17.png)

上面最后一张图（复用，并未实时截图）是不是很熟悉，没错也是 `sun.rmi.server.UnicastServerRef.exportObject(java.rmi.Remote, java.lang.Object, boolean)` 方法

区别不同的是标号1我们最后执行的逻辑是：

![rmi#6](resources/2022-07-17_11-29.png)

![rmi#20](resources/2022-07-17_12-08.png)

就是用反射创建了以当前类名为前缀， `_Stub` 为后缀的类的实例

回到前面，这里因为当前创建的stub是 `RemoteStub` 类型的：

![rmi#21](resources/2022-07-17_12-11.png)

所以它会去执行标号2的逻辑：

![rmi#22](resources/2022-07-17_12-13.png)

![rmi#23](resources/2022-07-17_12-14.png)

![rmi#24](resources/2022-07-17_12-17.png)

同样也是反射创建了以 `_Skel` 为后缀的类的实例

其余步骤就和创建服务对象没什么很大的区别了

3. 绑定服务

绑定服务只是往注册中心的 `Hashtable` 注册一下：

![rmi#25](resources/2022-07-17_17-34.png)

4. 客户端获取注册中心

![rmi#26](resources/2022-07-17_17-38.png)

注册中心的获取我们看下标注1：

![rmi#27](resources/2022-07-17_17-40.png)

![rmi#28](resources/2022-07-17_17-41.png)

![rmi#29](resources/2022-07-17_17-42.png)

![rmi#5](resources/2022-07-17_11-26.png)

没错，又跑到了上面最后一张图（复用，并未实时截图）。因为它存在以 `_Stub` 为后缀的类，所以该方法的返回值是 `RegistryImpl_Stub` 类型的实例

5. 客户端获取远程对象

![rmi#26](resources/2022-07-17_17-38.png)

获取远程对象看一下标号2：

![rmi#27](resources/2022-07-17_18-18.png)

`this.ref.newCall` 与注册中心建立连接，此时服务端阻塞的线程获取到了客户端的socket，会去提交一个处理连接的任务给线程池：

![rmi#28](resources/2022-07-17_19-41.png)

于是我们来看一下 `ConnectionHandler` 的run方法：

![rmi#29](resources/2022-07-17_19-45.png)

![rmi#30](resources/2022-07-17_19-46.png)

![rmi#31](resources/2022-07-17_19-49.png)

![rmi#32](resources/2022-07-17_19-55.png)

![rmi#33](resources/2022-07-17_19-58.png)

`ObjectTable.getTarget(new ObjectEndpoint(id, transport))` 是从 `ObjectTable` 中取出前面创建注册中心时注册的对象

`disp.dispatch(impl, call)` 会进入到前面创建注册中心时创建的骨架（skeleton）的dispatch方法中 ：

![rmi#34](resources/2022-07-17_21-11.png)

![rmi#35](resources/2022-07-17_21-12.png)

![rmi#36](resources/2022-07-17_21-14.png)

上图 `SharedSecrets.getJavaObjectInputStreamReadString().readString(in)` 在方法名尚未被客户端传输过来之前会阻塞在那里

回到前面客户端的步骤， `out.writeObject($param_String_1)` 把需要调用的方法写到输出流上

`this.ref.invoke(call)` 与注册中心交互，把要调用的方法名发送过去。然后服务端读取到方法名：

![rmi#36](resources/2022-07-17_21-14.png)

上图 `server.lookup($param_String_1)` 从注册中心的 `Hashtable` 里取出服务对象：

![rmi#37](resources/2022-07-17_21-18.png)

而 `out.writeObject($result)` 则是把服务对象写入输出流，但是在写的过程中，这里动了些手脚：

![rmi#38](resources/2022-07-17_21-38.png)

![rmi#39](resources/2022-07-17_21-40.png)

![rmi#40](resources/2022-07-17_21-41.png)

这里实际上是把我们创建服务对象时生成动态代理对象返回给了客户端

回到前面客户端的步骤，`$result = (Remote)in.readObject()` 反序列化远程对象

6. 客户端调用远程方法

因为客户端获取到的远程对象实际是服务端创建服务对象时产生的动态代理对象，所以我们看下 `RemoteObjectInvocationHandler` 的 `invoke` 方法：

![rmi#41](resources/2022-07-17_21-51.png)

![rmi#42](resources/2022-07-17_21-51_1.png)

![rmi#43](resources/2022-07-17_21-53.png)

上图首先序列化调用的参数写入输出流，此时服务端负责网络通信的线程监听到请求，会依次执行到这里：

![rmi#44](resources/2022-07-17_22-04.png)

先是取出要执行的 `Method` 对象，然后反序列化参数

![rmi#45](resources/2022-07-17_22-08.png)

反射调用相应的方法，序列化返回值

回到客户端 `sun.rmi.server.UnicastRef.invoke(java.rmi.Remote, java.lang.reflect.Method, java.lang.Object[], long)` 方法，客户端最后会在这反序列化远程方法返回值：

![rmi#46](resources/2022-07-24_11-06.png)

###### 总结

总的来说，rmi的整个工作流程可以用下面这几张图表示：

![rmi#47](resources/2022-07-24_21-10.png)

![rmi#48](resources/2022-07-24_21-12.png)

### spring rmi 简易流程

###### 例子

参考 `Server.groovy` 和 `Client.groovy`

###### 流程

1. 服务端

首先服务端定义了 `RmiServiceExporter` ，它实现了 `InitializingBean` 接口，所以我们来看下它的 `afterPropertiesSet` 方法：

![rmi#49](resources/2022-07-24_21-18.png)

![rmi#50](resources/2022-07-24_21-20.png)

因为在创建 `RmiServiceExporter` 时我们没有指定注册中心，所以这里先会去创建注册中心：

![rmi#51](resources/2022-07-24_21-23.png)

![rmi#52](resources/2022-07-24_21-23_1.png)

![rmi#53](resources/2022-07-24_21-24.png)

![rmi#54](resources/2022-07-24_21-32.png)

后面就是一些耳熟能详的事了

回到 `org.springframework.remoting.rmi.RmiServiceExporter.prepare` ：

![rmi#55](resources/2022-07-24_21-36.png)

因为这里我们服务类并没有实现 `Remote` 接口，所以它会执行到这：

![rmi#56](resources/2022-07-24_21-39.png)

这里首先来看一下 `getProxyForService` 方法：

![rmi#57](resources/2022-07-24_21-42.png)

挺熟悉的了，创建spring的aop代理

回到 `getObjectToExport` 方法，所以这里只是把服务对象的代理对象和其它一些信息封装到了 `RmiInvocationWrapper` 中

继续往上回到 `org.springframework.remoting.rmi.RmiServiceExporter.prepare` ：

![rmi#58](resources/2022-07-24_21-48.png)

它对前面封装的 `RmiInvocationWrapper` 创建了一个jdk代理并注册到了 `ObjectTable` 中，相当于继承了 `UnicastRemoteObject` 的作用

2. 客户端

客户端使用的是 `RmiProxyFactoryBean` ，因为它也间接实现了 `InitializingBean` 接口，所以这里也是先看下它的 `afterPropertiesSet` 方法：

![rmi#59](resources/2022-07-24_21-55.png)

它会先调用父类的 `afterPropertiesSet` 方法：

![rmi#60](resources/2022-07-24_21-56.png)

![rmi#61](resources/2022-07-24_21-57.png)

![rmi#62](resources/2022-07-24_21-59.png)

这里到了我们在jdk rmi 客户端流程中看过的地方，回到客户端最开始的 `afterPropertiesSet` 方法：

![rmi#63](resources/2022-07-24_22-18.png)

它有个创建代理对象的过程，它把自身作为通知传给了代理工厂，而它自身又继承了 `RmiClientInterceptor` 类：

![rmi#64](resources/2022-07-24_22-20.png)

在客户端从容器中获取到bean后准备调用远程方法时，首先先执行了 `RmiClientInterceptor` 的 `invoke` 方法：

![rmi#65](resources/2022-07-24_22-22.png)

![rmi#66](resources/2022-08-07_18-56.png)

因为前面服务端创建时对 `RmiInvocationWrapper` 创建了jdk代理，所以这里获取到的存根类型是 `RmiInvocationHandler` 类型的，程序会去执行上图红框位置方法：

![rmi#67](resources/2022-08-07_19-13.png)

此时因为存根又是 `RemoteObjectInvocationHandler` 类型的代理，所以程序又会去执行 `RemoteObjectInvocationHandler` 的 `invoke` 方法，这里就又回到了jdk rmi的流程中。

之后程序执行到了：

![rmi#68](resources/2022-08-07_20-48.png)

![rmi#69](resources/2022-08-07_20-49.png)

![rmi#70](resources/2022-08-07_20-49_1.png)

![rmi#71](resources/2022-08-07_20-50.png)

![rmi#72](resources/2022-08-07_20-52.png)

最后就是将方法执行的结果序列化返回

###### 总结

总体上流程和jdk rmi是一致的，区别在于spring封装了rmi服务端和客户端的创建，并且远程对象也是经过spring处理的

### 替代方案

高版本的spring中，rmi已经被标记为了deprecated，所以它的替代方案为：

* Jax-WS
* Spring Web Services
* AMQP
