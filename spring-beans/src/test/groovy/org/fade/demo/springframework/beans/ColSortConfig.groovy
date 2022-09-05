package org.fade.demo.springframework.beans

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ColSortConfig {

	@Bean
	ColBean colBeanImpl1() {
		new ColBeanImpl1()
	}

	@Bean
	ColBean colBeanImpl2() {
		new ColBeanImpl2()
	}

	@Bean
	ColBeanContainer colBeanContainer() {
		new ColBeanContainer()
	}

}
