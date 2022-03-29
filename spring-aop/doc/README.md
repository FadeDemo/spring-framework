# spring-aop

### CGLIB代理和JDK代理的使用场景

todo

### 几种通知类型的执行顺序

todo

### AOP的整个流程

XML方式（以测试资源目录下的 `aopExample.xml` 中的 `testBean` 的获取为例 ）：

1. 解析XML文件时，在 `AopNamespaceHandler#init` 中注册 `<aop:aspectj-autoproxy />` 标签对应的解析器 `AspectJAutoProxyBeanDefinitionParser`

![aop#1](resources/2022-03-29_21-19.png)

2. 调用 `AspectJAutoProxyBeanDefinitionParser#parse` 方法注册 `AnnotationAwareAspectJAutoProxyCreator` 

![aop#2](resources/2022-03-29_21-26.png)

3. 因为 `AnnotationAwareAspectJAutoProxyCreator` 实现了 `BeanPostProcessor` 接口，所以会在 `AbstractApplicationContext#registerBeanPostProcessors` 中把它注册到 `AbstractBeanFactory` 的 `beanPostProcessors` 属性中

![aop#3](resources/2022-03-29_21-41.png)

4. 又因为 `AnnotationAwareAspectJAutoProxyCreator` 实现的是 `BeanPostProcessor` 的子接口 `InstantiationAwareBeanPostProcessor` ，所以在获取bean时，它的 `applyBeanPostProcessorsBeforeInstantiation` 方法和 `applyBeanPostProcessorsAfterInitialization` 方法会被 `AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation` 调用

![aop#4](resources/2022-03-29_21-54.png)

5. 

### AOP概念与Spring中实现的对应关系

* 切面 —— `@Aspect` 注解、 `Advisor` 接口及其实现类
* 通知 —— `Advice` 接口及其实现类、 `@Before` 、 `@After` 、 `@Around` 、 `@AfterThrowing` 、 `@AfterReturing` 注解
* 