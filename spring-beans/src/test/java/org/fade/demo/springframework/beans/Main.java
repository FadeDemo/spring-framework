package org.fade.demo.springframework.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
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

	@Test
	public void testNestedBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/parseTest.xml");
		NestedBean testNestedBean = context.getBean("testNestedBean", NestedBean.class);
		testNestedBean.getUser().showMe();
	}

	@Test
	public void testFactoryBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/getBeanTest.xml");
		Object testFactoryBean = context.getBean("testFactoryBean");
//		assert testFactoryBean instanceof CarFactoryBean;
		assert testFactoryBean instanceof Car;
		// 获取工厂bean本身要加&
		Object bean = context.getBean("&testFactoryBean");
//		assert bean instanceof Car;
		assert bean instanceof CarFactoryBean;
		Car car = context.getBean("testFactoryBean", Car.class);
		System.out.println(car);
	}

	@Test
	public void testConstructorCyclicDependencies() {
		// 构造器循环依赖无法解决
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/constructorCyclicDependencies.xml");
		} catch (Exception e) {
			assert e.getCause().getCause().getCause() instanceof BeanCurrentlyInCreationException;
			System.out.println(e);
		}
	}

	@Test
	public void testSingletonSetterCyclicDependencies() {
		// 可以解决单例的setter循环依赖
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/setterCyclicDependencies.xml");
		TestA testASingleton = context.getBean("testASingleton", TestA.class);
		assert testASingleton != null;
//		testASingleton.a();
	}

	@Test
	public void testPrototypeSetterCyclicDependencies() {
		// 原型setter循环依赖无法解决
		// 而且异常产生的时机与构造器循环依赖不同
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/setterCyclicDependencies.xml");
			TestA testASingleton = context.getBean("testAPrototype", TestA.class);
		} catch (Exception e) {
			assert e.getCause().getCause().getCause() instanceof BeanCurrentlyInCreationException;
			System.out.println(e);
		}
	}

}
