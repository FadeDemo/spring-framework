# spring-beans

### 核心的两个类

* `BefaultListableBeanFactory`
* `XmlBeanDefinitionReader`

### XML配置文件的读取流程

XML配置文件 ---> `Resource` ---> `Document` ---> `Element` ---> `BeanDefinition` 

