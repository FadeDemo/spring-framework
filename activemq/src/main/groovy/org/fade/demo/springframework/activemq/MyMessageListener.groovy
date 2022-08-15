package org.fade.demo.springframework.activemq

import javax.jms.Message
import javax.jms.MessageListener

/**
*   消息监听器
*
* @author fade
* @date 2022 /08/15
*/
class MyMessageListener implements MessageListener{

	@Override
	void onMessage(Message message) {
		println "listen: ${message.getText()}"
	}

}
