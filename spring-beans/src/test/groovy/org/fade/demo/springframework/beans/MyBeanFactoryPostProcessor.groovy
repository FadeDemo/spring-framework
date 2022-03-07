package org.fade.demo.springframework.beans

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanDefinitionVisitor
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory

class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	Set<String> container

	MyBeanFactoryPostProcessor() {
		this.container = new HashSet<>(16)
	}

	@Override
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		def beanNames = beanFactory.getBeanDefinitionNames()
		for (i in beanNames) {
			def bd = beanFactory.getBeanDefinition(i)
			def visitor = new BeanDefinitionVisitor((x) -> {
				if (isObscene(x)) {
					"******"
				} else {
					x
				}
			})
			visitor.visitBeanDefinition(bd)
		}
	}

	def isObscene(def value) {
		def potentialObscene = value.toString().toUpperCase()
		this.container.contains(potentialObscene)
	}

	void setContainer(Set<String> container) {
		this.container.clear()
		container.each {this.container.add it.toUpperCase()}
	}

}
