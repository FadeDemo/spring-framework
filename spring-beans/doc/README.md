# spring-beans

### 核心的几个类

* `BefaultListableBeanFactory`
* `XmlBeanDefinitionReader`
* `AbstarctBeanFactory`

### XML配置文件的读取流程

XML配置文件 ---> `Resource` ---> `Document` ---> `Element` ---> `BeanDefinition` 

### 获取bean的入口

`getBean()` 方法

### 循环依赖问题

在使用Spring框架时碰到循环依赖是很经常的事，目前主要碰到的有这几种：

* 构造器循环依赖（Spring不能解决）
* 单例setter依赖（Spring可以解决）
* 原型setter依赖（Spring不能解决）

这里描述下Spring是如何解决单例setter依赖的：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="testASingleton" class="org.fade.demo.springframework.beans.TestA">
        <property name="testB" ref="testBSingleton" />
    </bean>

    <bean id="testBSingleton" class="org.fade.demo.springframework.beans.TestB">
        <property name="testC" ref="testCSingleton" />
    </bean>

    <bean id="testCSingleton" class="org.fade.demo.springframework.beans.TestC">
        <property name="testA" ref="testASingleton" />
    </bean>

</beans>
```

首先创建 `testASingleton` -> 暴露 `ObjectFactory` ，将其加入三级缓存 `singletonFactories` -> 检测到依赖 `testBSingleton` ，创建 `testBSingleton` -> 暴露 `ObjectFactory` ，将其加入三级缓存 -> 检测到依赖 `testCSingleton` ，创建 `testCSingleton` -> 暴露 `ObjectFactory` ，将其加入三级缓存 -> 检测到依赖 `testASingleton` -> 从三级缓存中获取 `testASingleton` ，获取到后从三级缓存中移除它，并加入到二级缓存 `earlySingletonObjects` 中 -> `testCSingleton` 创建完毕，从前两级缓存中移除它，并加入到一级缓存 `singletonObjects` 中 -> `testBSingleton` 创建完毕，从前两级缓存中移除它，并加入到一级缓存 `singletonObjects` 中 -> `testASingleton` 创建完毕，从前两级缓存中移除它，并加入到一级缓存 `singletonObjects` 中

总结，Spring解决单例setter依赖使用了三级缓存：

* `singletonObjects` （一级缓存）
* `earlySingletonObjects` （二级缓存）
* `singletonFactories` （三级缓存）

其中，使用 `ObjectFactory` 的目的是为了延迟代理对象的创建，至于为什么要使用三级缓存，而不使用二级缓存，这个网上说法很多。但是可以明确的是，直接使用二级缓存也是可以的，比如：

```java
protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
    Assert.notNull(singletonFactory, "Singleton factory must not be null");
    synchronized (this.singletonObjects) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);	
        }
    }
}
```

把上面的代码改成：

```java
protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
    Assert.notNull(singletonFactory, "Singleton factory must not be null");
    synchronized (this.singletonObjects) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.earlySingletonObjects.put(beanName, singletonFactory.getObject());
            this.registeredSingletons.add(beanName);	
        }
    }
}
```

这依旧可以解决循环依赖。

个人倾向于认为这样设计是为了不破坏Spring的设计原则，比如说单一职责原则， `singletonObjects` 负责缓存创建好并初始化好的实例， `earlySingletonObjects` 负责缓存创建好但未初始化好的实例， `singletonFactories` 负责缓存 `ObjectFactory` 。
