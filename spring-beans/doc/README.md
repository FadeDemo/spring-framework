# spring-beans

### 核心的几个类

* `BefaultListableBeanFactory`
* `XmlBeanDefinitionReader`
* `AbstarctBeanFactory`

### XML配置文件的读取流程

XML配置文件 ---> `Resource` ---> `Document` ---> `Element` ---> `BeanDefinition` 

### 获取bean的入口

`getBean()` 方法

