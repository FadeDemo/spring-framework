package org.fade.demo.springframework.transaction;

/**
 * @author fade
 * @date 2022/01/03
 */
public interface PropagationService {

	void nestedThrowException(User user);

	void nestedOk(User user);

}
