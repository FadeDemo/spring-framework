package org.fade.demo.springframework.rmi

import org.springframework.context.support.ClassPathXmlApplicationContext

class Server {

	static void main(String[] args) {
		new ClassPathXmlApplicationContext("server.xml")
	}

}
