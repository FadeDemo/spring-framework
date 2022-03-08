package org.fade.demo.springframework.beans

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor

class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{

	@Override
	Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		println "**********************************"
		null
	}

}
