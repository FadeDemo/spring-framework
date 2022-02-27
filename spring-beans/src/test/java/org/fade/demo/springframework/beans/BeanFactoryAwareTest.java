package org.fade.demo.springframework.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author fade
 * @date 2022/02/27
 */
public class BeanFactoryAwareTest implements BeanFactoryAware {

	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public void testAware() {
		Hello hello = beanFactory.getBean("hello", Hello.class);
		hello.say();
	}

}
