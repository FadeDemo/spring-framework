package org.fade.demo.springframework.rmi

import java.rmi.Naming

class JdkRmiClient {

	static void main(String[] args) {
		def service = Naming.lookup("rmi://127.0.0.1:9999/add")
		println service.add(1, 2)
	}

}
