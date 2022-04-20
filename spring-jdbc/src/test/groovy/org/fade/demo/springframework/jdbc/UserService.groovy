package org.fade.demo.springframework.jdbc

interface UserService {

	/**
	 * <p>保存</p>
	 * @param user {@link User}
	 * */
	void save(User user)

	/**
	 * <p>获取所有用户</p>
	 * @return 所有用户
	 * @see User
	 * */
	List<User> getUsers()

}