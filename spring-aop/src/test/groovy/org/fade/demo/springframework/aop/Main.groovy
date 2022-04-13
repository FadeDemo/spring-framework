package org.fade.demo.springframework.aop

import org.junit.jupiter.api.Test
import org.springframework.cglib.proxy.Enhancer
import org.springframework.context.support.ClassPathXmlApplicationContext

class Main {

	@Test
	void aopExample() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/aop/aopExample.xml")
		def testBean = context.getBean("testBean", TestBean)
		testBean.test()
	}

	@Test
	void aopJdkExample() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/aop/aopExample.xml")
		def userService = context.getBean("userService", UserService)
		userService.add()
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

	@Test
	void staticAopExample() {
		// need to configure -javaagent:/absolute/path/to/spring-instrument.jar
		// the difference between aop dynamic proxy and aop static proxy is aop dynamic
		// proxy need a procedure which create a proxy object, but aop static proxy achieve
		// aop through changing the bytecode
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/aop/staticAopExample.xml")
		def testBean = context.getBean("testBean", TestBean)
		testBean.test()
	}

	@Test
	void aopWithNoAspectAnnotation() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/aop/aopWithNoAspectAnnotation.xml")
		def demoService = context.getBean("demoService", DemoService)
		demoService.test()
	}

}
