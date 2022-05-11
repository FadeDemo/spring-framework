package org.fade.demo.springframework.jdbc.scan

import org.springframework.stereotype.Component

@Component
class ScannedBean {

	void doSomething() {
		println "I am scanned!"
	}

}
