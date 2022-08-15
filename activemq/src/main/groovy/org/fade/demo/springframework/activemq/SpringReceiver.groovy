package org.fade.demo.springframework.activemq

import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.jms.core.JmsTemplate

import javax.jms.Message

/**
*   spring接收者
*
* @author fade
* @date 2022 /08/15
*/
class SpringReceiver {

	static void main(String[] args) {
		def context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml")
		def jmsTemplate = context.getBean("jmsTemplate", JmsTemplate)
		def queue = context.getBean("queue", ActiveMQQueue)
		def receive = jmsTemplate.receive(queue)
		println receive.getText()
	}

}
