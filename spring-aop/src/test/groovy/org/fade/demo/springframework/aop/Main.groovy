package org.fade.demo.springframework.aop

import org.junit.jupiter.api.Test
import org.springframework.cglib.proxy.Enhancer
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
		//  and when run mode get different result from debug mode
		UserService userService = new UserServiceImpl();
		def handler = new MyInvocationHandler(userService)
		def proxy = handler.getProxy()
		proxy.add()
	}

	@Test
	void cglibProxyExample() {
		// fixme unreasonable result
		//  and when run mode get different result from debug mode
		def enhancer = new Enhancer();
		enhancer.setSuperclass(TestBean)
		enhancer.setCallback(new MyMethodInterceptor())
		def instance = enhancer.create()
		instance.test()
		println instance
	}

}
