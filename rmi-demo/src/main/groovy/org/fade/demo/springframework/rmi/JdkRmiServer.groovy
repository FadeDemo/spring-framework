package org.fade.demo.springframework.rmi

import java.rmi.registry.LocateRegistry

class JdkRmiServer {

	static void main(String[] args) {
		def service = new JdkRmiServiceImpl()
		def registry = LocateRegistry.createRegistry(9999)
		registry.bind("add", service)
	}

}
