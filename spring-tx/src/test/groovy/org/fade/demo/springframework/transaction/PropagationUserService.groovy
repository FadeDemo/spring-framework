package org.fade.demo.springframework.transaction

import jakarta.annotation.Resource
import org.springframework.stereotype.Service

@Service
class PropagationUserService {

	@Resource
	private PropagationUserService1 propagationUserService1

	@Resource
	private PropagationUserService2 propagationUserService2

	void noTransactionExceptionRequiredRequired(){
		def user1 = new PropagationUser(name: "张三")
		propagationUserService1.addRequired(user1)
		def user2 = new PropagationUser(name: "李四")
		propagationUserService2.addRequired(user2)
		throw new RuntimeException()
	}

}
