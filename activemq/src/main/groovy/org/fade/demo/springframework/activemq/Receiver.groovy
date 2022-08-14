package org.fade.demo.springframework.activemq

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
