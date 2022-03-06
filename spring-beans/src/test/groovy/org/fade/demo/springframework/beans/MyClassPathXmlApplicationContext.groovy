package org.fade.demo.springframework.beans

import org.springframework.context.support.ClassPathXmlApplicationContext

class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

	MyClassPathXmlApplicationContext(String... configLocations) {
		super(configLocations)
	}

	@Override
	protected void initPropertySources() {
		this.getEnvironment().setRequiredProperties("TEST")
	}

}
