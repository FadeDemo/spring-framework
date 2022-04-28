package org.fade.demo.springframework.jdbc

interface UserService {

	/**
	 * <p>保存</p>
	 * @param user {@link User}
	 * */
	void save(User user)

	/**
	 * <p>保存并抛出异常
	 * <p>当传递的参数和参数类型数组长度一致
	 * */
	void saveThrowException(User user)

	/**
	 * <p>正常保存
	 * <p>当传递的参数和参数类型数组长度一致，且满足参数占位符数量多于参数数组长度（=参数类型数组长度），
	 * 并且参数类型数组内容正确设置</p>
	 * */
	void saveWithoutArrayIndexOutOfBound(User user)

	/**
	 * <p>获取所有用户</p>
	 * @return 所有用户
	 * @see User
	 * */
	List<User> getUsers()

}