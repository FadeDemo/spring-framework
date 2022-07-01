package org.fade.demo.springframework.rmi

import org.springframework.context.support.ClassPathXmlApplicationContext

class Client {

	static void main(String[] args) {
		def context = new ClassPathXmlApplicationContext("client.xml")
		def rmiService = context.getBean("client", RmiService)
		println rmiService.add(1, 2)
	}

}
