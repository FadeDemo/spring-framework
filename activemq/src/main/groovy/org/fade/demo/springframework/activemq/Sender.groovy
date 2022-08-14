package org.fade.demo.springframework.activemq

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
