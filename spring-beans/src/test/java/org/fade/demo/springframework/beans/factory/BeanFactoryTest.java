package org.fade.demo.springframework.beans.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {

	@Test
	public void testXmlBeanFactory() {
		// todo 废弃类的替代方法
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("org/fade/demo/springframework/beans/factory/beanFactoryTest.xml"));
	}

}
