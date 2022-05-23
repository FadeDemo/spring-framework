package org.fade.demo.springframework.transaction

import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

	void noTransactionRequiredRequiredException() {
		def user1 = new PropagationUser(name: "张四")
		propagationUserService1.addRequired(user1)
		def user2 = new PropagationUser(name: "李五")
		propagationUserService2.addRequiredException(user2)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionExceptionRequiredRequired() {
		def user1 = new PropagationUser(name: "张五")
		propagationUserService1.addRequired(user1)
		def user2 = new PropagationUser(name: "李六")
		propagationUserService2.addRequired(user2)
		throw new RuntimeException()
	}

	@Transactional(rollbackFor = Throwable)
	void transactionRequiredRequiredException() {
		def user1 = new PropagationUser(name: "张六")
		propagationUserService1.addRequired(user1)
		def user2 = new PropagationUser(name: "李七")
		propagationUserService2.addRequiredException(user2)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionTryRequiredRequiredException() {
		def user1 = new PropagationUser(name: "张七")
		propagationUserService1.addRequired(user1)
		def user2 = new PropagationUser(name: "李八")
		try {
			propagationUserService2.addRequiredException(user2)
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

}
