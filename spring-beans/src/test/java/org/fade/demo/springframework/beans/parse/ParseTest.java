package org.fade.demo.springframework.beans.parse;

import org.fade.demo.springframework.beans.parse.lookupmethod.GetBeanTest;
import org.fade.demo.springframework.beans.parse.replacedmethod.TestChangeMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ParseTest {

	@Test
	public void testParseMeta() {
		// todo 获取meta属性
	}

	@Test
	@SuppressWarnings("all")
	public void testLookupMethod() {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("org/fade/demo/springframework/beans/parse/parseTest.xml"));
		GetBeanTest test = factory.getBean("getBeanTest", GetBeanTest.class);
		test.showMe();
	}

	@Test
	@SuppressWarnings("all")
	public void testReplacedMethod() {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("org/fade/demo/springframework/beans/parse/parseTest.xml"));
		TestChangeMethod test = factory.getBean("testChangeMethod", TestChangeMethod.class);
		test.changeMe();
	}

}
