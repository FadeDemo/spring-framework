package org.fade.demo.springframework.transaction

import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PropagationUserService2 {

	@Resource
	private PropagationUserRepository propagationUserRepository

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequired(PropagationUser user){
		propagationUserRepository.save(user)
	}

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequiredException(PropagationUser user){
		propagationUserRepository.save(user)
		throw new RuntimeException()
	}

}
