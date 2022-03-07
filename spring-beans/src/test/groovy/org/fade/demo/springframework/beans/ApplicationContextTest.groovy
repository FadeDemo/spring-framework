package org.fade.demo.springframework.beans

import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext

class ApplicationContextTest {

	@Test
	void testOverrideInitPropertySources() {
		try {
			ApplicationContext context = new MyClassPathXmlApplicationContext("org/fade/demo/springframework/beans/awareTest.xml")
			def testBeanFactoryAware = context.getBean("testBeanFactoryAware", BeanFactoryAwareTest.class)
			testBeanFactoryAware.testAware()
		} catch(Exception e) {
			e.printStackTrace()
		}
	}

}
