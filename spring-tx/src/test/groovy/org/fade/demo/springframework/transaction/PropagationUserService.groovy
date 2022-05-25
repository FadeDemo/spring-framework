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

	void noTransactionExceptionRequiresNewRequiresNew() {
		def user1 = new PropagationUser(name: "张八")
		propagationUserService1.addRequiredNew(user1)
		def user2 = new PropagationUser(name: "李九")
		propagationUserService2.addRequiredNew(user2)
		throw new RuntimeException()
	}

	void noTransactionRequiresNewRequiresNewException() {
		def user1 = new PropagationUser(name: "张九")
		propagationUserService1.addRequiredNew(user1)
		def user2 = new PropagationUser(name: "李十")
		propagationUserService2.addRequiredNewException(user2)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionExceptionRequiresNewRequiresNew() {
		def user1 = new PropagationUser(name: "张十")
		propagationUserService1.addRequiredNew(user1)
		def user2 = new PropagationUser(name: "李十一")
		propagationUserService2.addRequiredNew(user2)
		def user3 = new PropagationUser(name: "王五")
		propagationUserService1.addRequired(user3)
		throw new RuntimeException()
	}

	@Transactional(rollbackFor = Throwable)
	void transactionRequiresNewRequiresNewException() {
		def user1 = new PropagationUser(name: "张十一")
		propagationUserService1.addRequiredNew(user1)
		def user2 = new PropagationUser(name: "李十二")
		propagationUserService2.addRequiredNewException(user2)
		def user3 = new PropagationUser(name: "王六")
		propagationUserService1.addRequired(user3)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionTryRequiresNewRequiresNewException() {
		def user1 = new PropagationUser(name: "张十二")
		propagationUserService1.addRequiredNew(user1)
		def user2 = new PropagationUser(name: "李十三")
		try {
			propagationUserService2.addRequiredNewException(user2)
		} catch(Exception e) {
			e.printStackTrace()
		}
		def user3 = new PropagationUser(name: "王七")
		propagationUserService1.addRequired(user3)
	}

	void noTransactionExceptionNestedNested() {
		def user1 = new PropagationUser(name: "张十三")
		propagationUserService1.addNested(user1)
		def user2 = new PropagationUser(name: "李十四")
		propagationUserService2.addNested(user2)
		throw new RuntimeException()
	}

	void noTransactionNestedNestedException() {
		def user1 = new PropagationUser(name: "张十四")
		propagationUserService1.addNested(user1)
		def user2 = new PropagationUser(name: "李十五")
		propagationUserService2.addNestedException(user2)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionExceptionNestedNested() {
		def user1 = new PropagationUser(name: "张十五")
		propagationUserService1.addNested(user1)
		def user2 = new PropagationUser(name: "李十六")
		propagationUserService2.addNested(user2)
		throw new RuntimeException()
	}

	@Transactional(rollbackFor = Throwable)
	void transactionNestedNestedException() {
		def user1 = new PropagationUser(name: "张十六")
		propagationUserService1.addNested(user1)
		def user2 = new PropagationUser(name: "李十七")
		propagationUserService2.addNestedException(user2)
	}

	@Transactional(rollbackFor = Throwable)
	void transactionTryNestedNestedException() {
		def user1 = new PropagationUser(name: "张十七")
		propagationUserService1.addNested(user1)
		def user2 = new PropagationUser(name: "李十八")
		try {
			propagationUserService2.addNestedException(user2)
		} catch(Exception e) {
			e.printStackTrace()
		}
	}

}
