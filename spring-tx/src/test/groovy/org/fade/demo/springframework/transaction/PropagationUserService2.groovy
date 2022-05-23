package org.fade.demo.springframework.transaction

import jakarta.annotation.Resource
import org.fade.demo.springframework.transaction.dao.PropagationUserMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PropagationUserService2 {

	@Resource
	private PropagationUserMapper propagationUserMapper

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequired(PropagationUser user){
		propagationUserMapper.insert(user)
	}

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequiredException(PropagationUser user){
		propagationUserMapper.insert(user)
		throw new RuntimeException()
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void addRequiredNew(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void addRequiredNewException(PropagationUser user) {
		propagationUserMapper.insert(user)
		throw new RuntimeException()
	}

}
