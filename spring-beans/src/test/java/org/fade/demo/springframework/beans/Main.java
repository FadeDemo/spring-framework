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

	@Test
	public void testAlias() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		Teacher testAlias = context.getBean("testAlias", Teacher.class);
		Teacher gardener = context.getBean("gardener", Teacher.class);
		Teacher candle = context.getBean("candle", Teacher.class);
		assert testAlias == gardener;
		assert gardener == candle;
		candle.showMe();
	}

	@Test
	public void testCustomElement() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		CustomElementBean bean = context.getBean("1", CustomElementBean.class);
		System.out.println(bean);
	}

}
