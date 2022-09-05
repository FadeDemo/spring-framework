package org.fade.demo.springframework.beans

import org.springframework.beans.BeansException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.core.annotation.AnnotationAwareOrderComparator

class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

//	private BeanFactory beanFactory

	MyClassPathXmlApplicationContext(String... configLocations) {
		super(configLocations)
	}

	@Override
	protected void initPropertySources() {
//		this.getEnvironment().setRequiredProperties("TEST")
	}

//	@Override
//	void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		this.beanFactory = beanFactory
//	}

//	@Override
//	void afterPropertiesSet() {
//		super.afterPropertiesSet()
//		this.beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE)
//	}

	@Override
	protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		super.prepareBeanFactory(beanFactory)
		beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE)
	}

}
