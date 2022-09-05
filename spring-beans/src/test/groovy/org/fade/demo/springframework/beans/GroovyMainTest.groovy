package org.fade.demo.springframework.beans

import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.core.annotation.AnnotationAwareOrderComparator

import java.text.MessageFormat

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

	@Test
	void testBeanPostProcessor() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/beanPostProcessorTest.xml")
		def car = context.getBean("testFactoryBean", Car)
		println car.price
		println car.brand
		println car.maxSpeed
	}

	@Test
	void testMessageFormat() {
		def pattern = "{0}，您好！您于{1}在工商银行存入了{2}元"
		def params = ["john", new GregorianCalendar().getTime(), 1.0E3] as Object[]
		println MessageFormat.format(pattern, params)
		println new MessageFormat(pattern, Locale.US).format(params)
	}

	@Test
	void testMessageSource() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/messageSourceTest.xml")
		println context.getMessage("test", null, Locale.CHINA)
		println context.getMessage("test", null, Locale.US)
	}

	@Test
	void testApplicationListener() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/applicationListenerTest.xml")
		def event = new TestEvent("fade")
		event.message = "hello world"
		context.publishEvent(event)
	}

	@Test
	void testConverter() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/converterTest.xml")
		def conversionService = context.getBean("conversionService")
		def date = conversionService.convert("1949-10-01", Date)
		println date
	}

	@Test
	void testListSort() {
		// 单纯的 xml context 无法处理Ordered接口或@Order注解
//		context.getBeanFactory().setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE)
		def context = new MyClassPathXmlApplicationContext("org/fade/demo/springframework/beans/listSort.xml")
		// 注解上下文暂时需配合 --add-opens java.base/java.lang=ALL-UNNAMED 使用
//		def context = new AnnotationConfigApplicationContext(ColSortConfig)
		def container = context.getBean("colBeanContainer", ColBeanContainer)
		container.list.forEach { it.test() }
	}

}
