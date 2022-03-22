package org.fade.demo.springframework.aop

import org.junit.jupiter.api.Test
import org.springframework.context.support.ClassPathXmlApplicationContext

class Main {

	@Test
	void aopExample() {
		// todo @Around @Before @After 执行顺序
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/aop/aopExample.xml")
		def testBean = context.getBean("testBean", TestBean)
		testBean.test()
	}

	@Test
	void jdkProxyExample() {
		// fixme unreasonable result
		UserService userService = new UserServiceImpl();
		def handler = new MyInvocationHandler(userService)
		def proxy = handler.getProxy()
		proxy.add()
	}

}
