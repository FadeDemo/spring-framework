package org.fade.demo.springframework.beans

import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

class GroovyMainTest {

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

	@Test
	void testPropertyEditor() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/propertyEditorTest.xml")
		def userManager = context.getBean("userManager", UserManager)
		println userManager
	}

	@Test
	void testBeanFactoryPostProcessor() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/beanFactoryPostProcessorTest.xml")
		def processor = context.getBean("myBeanFactoryPostProcessor", MyBeanFactoryPostProcessor)
		processor.postProcessBeanFactory(context.autowireCapableBeanFactory)
		def simple = context.getBean("simpleBean", SimpleBean)
		println simple
	}

}
