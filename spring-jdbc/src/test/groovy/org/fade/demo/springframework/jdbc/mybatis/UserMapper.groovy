package org.fade.demo.springframework.jdbc.mybatis

import org.fade.demo.springframework.jdbc.User

interface UserMapper {

	void insertUser(User user)

	User getUser(Integer id)

}