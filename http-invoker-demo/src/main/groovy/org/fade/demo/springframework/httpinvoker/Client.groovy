package org.fade.demo.springframework.httpinvoker

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
*   客户端
*
* @author fade
* @date 2022 /08/08
*/
class Client {

	static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:client.xml")
		def httpInvokerService = context.getBean("httpInvokerService", HttpInvokerService)
		println httpInvokerService.test("client msg")
	}

}
