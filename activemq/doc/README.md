# jms

### jdk jms

jdk jms 的用法如下所示：

* 发送端

```groovy
import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Session
/**
*   发送方
*
* @author fade
* @date 2022 /08/14
*/
class Sender {

	static void main(String[] args) {
		def connectionFactory = new ActiveMQConnectionFactory()
		def connection = connectionFactory.createConnection()
		def session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE)
		def queue = session.createQueue("test")
		def producer = session.createProducer(queue)
		def message = session.createTextMessage("test info")
		producer.send(message)
		session.commit()
		session.close()
		connection.close()
	}

}
```

* 接受端

```groovy
import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Session
/**
*   接收者
*
* @author fade
* @date 2022 /08/14
*/
class Receiver {

	static void main(String[] args) {
		def connectionFactory = new ActiveMQConnectionFactory()
		def connection = connectionFactory.createConnection()
		connection.start()
		def session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE)
		def queue = session.createQueue("test")
		def consumer = session.createConsumer(queue)
		def receive = consumer.receive()
		println receive.getText()
		session.commit()
		session.close()
		connection.close()
	}

}
```

可以总结为：

1. 创建连接工厂
2. 获取连接
3. 创建会话
4. 创建队列
5. 利用会话创建生产者/消费者
6. 如果是生产者，利用会话创建消息
7. 发送消息/接受消息
8. 提交会话
9. 关闭会话
10. 关闭连接

### spring jms

**spring jms 只不过是对jdk jms的操作进行了spring式的封装，比如对象的创建纳入spring容器及操作模板化**

spring jms操作的模板是 `JmsTemplate` ，它与 `JdbcTemplate` 的逻辑类似。

首先我们来看发送端：

![jms#1](resources/2022-08-21_21-48.png)

![jms#2](resources/2022-08-21_21-51.png)

![jms#3](resources/2022-08-21_21-53.png)

![jms#4](resources/2022-08-21_21-54.png)

![jms#5](resources/2022-08-21_21-55.png)

![jms#6](resources/2022-08-21_21-56.png)

![jms#7](resources/2022-08-21_21-57.png)

如果配置了监听器相关的bean，比如 `org.springframework.jms.listener.DefaultMessageListenerContainer` ，则发送端还会多一段这个流程：

![jms#14](resources/2022-08-21_22-10.png)

![jms#15](resources/2022-08-21_22-11.png)

![jms#16](resources/2022-08-21_22-12.png)

![jms#17](resources/2022-08-21_22-12_1.png)

![jms#18](resources/2022-08-21_22-13.png)

![jms#19](resources/2022-08-21_22-16.png)

在上图可以看到这里有有关于调用监听器的逻辑

![jms#20](resources/2022-08-21_22-20.png)

![jms#21](resources/2022-08-21_22-20_1.png)

![jms#22](resources/2022-08-21_22-22.png)

![jms#23](resources/2022-08-21_22-24.png)

![jms#24](resources/2022-08-21_22-25.png)

再来看看接受端：

![jms#8](resources/2022-08-21_21-58.png)

![jms#9](resources/2022-08-21_21-59.png)

看到这里是不是很熟悉，也是调用了 `execute` 方法，联想到 `JdbcTemplate` ，这里也应该是通过传递不同的回调用于区分不同的操作

![jms#2](resources/2022-08-21_21-51.png)

![jms#3](resources/2022-08-21_21-53.png)

![jms#10](resources/2022-08-21_22-02.png)

![jms#11](resources/2022-08-21_22-04.png)

![jms#12](resources/2022-08-21_22-05.png)

![jms#13](resources/2022-08-21_22-05_1.png)
