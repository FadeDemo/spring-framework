package org.fade.demo.springframework.activemq

import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.jms.core.JmsTemplate

/**
*   spring发送者
*
* @author fade
* @date 2022 /08/15
*/
class SpringSender {

	static void main(String[] args) {
		def context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml")
		def jmsTemplate = context.getBean("jmsTemplate", JmsTemplate)
		def queue = context.getBean("queue", ActiveMQQueue)
		jmsTemplate.send(queue, x -> x.createTextMessage("spring test"))
	}

}
