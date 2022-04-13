package org.fade.demo.springframework.aop

class DemoService {

	@DemoAnnotation
	void test() {
		println "test"
	}

}
