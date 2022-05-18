package org.fade.demo.springframework.transaction

import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class Main {

	@Test
	void requiredTest1() {
		def context = new AnnotationConfigApplicationContext(PropagationConfig)
		def service = context.getBean(PropagationUserService)
		service.noTransactionExceptionRequiredRequired()
	}

}
