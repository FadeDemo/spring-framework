package org.fade.demo.springframework.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fade
 * @date 2022/01/05
 */
public class Main {

	@Test
	public void testLookupMethod() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		GetBeanTest bean = context.getBean("getBeanTest", GetBeanTest.class);
		bean.showMe();
	}

	@Test
	public void testReplacedMethod() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		TestChangeMethod testReplacedMethod = context.getBean("testReplacedMethod", TestChangeMethod.class);
		testReplacedMethod.changeMe();
	}

	@Test
	public void testConstructorArg() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		ConstructorArgBean testConstructorArg = context.getBean("testConstructorArg", ConstructorArgBean.class);
		System.out.println(testConstructorArg);
	}

}
