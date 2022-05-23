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

	@Test
	void requiredTest2() {
		def context = new AnnotationConfigApplicationContext(PropagationConfig)
		def service = context.getBean(PropagationUserService)
		service.noTransactionRequiredRequiredException()
	}

	@Test
	void requiredTest3() {
		def context = new AnnotationConfigApplicationContext(PropagationConfig)
		def service = context.getBean(PropagationUserService)
		service.transactionExceptionRequiredRequired()
	}

	@Test
	void requiredTest4() {
		def context = new AnnotationConfigApplicationContext(PropagationConfig)
		def service = context.getBean(PropagationUserService)
		service.transactionRequiredRequiredException()
	}

	@Test
	void requiredTest5() {
		def context = new AnnotationConfigApplicationContext(PropagationConfig)
		def service = context.getBean(PropagationUserService)
		service.transactionTryRequiredRequiredException()
	}

}
