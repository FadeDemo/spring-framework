package org.fade.demo.springframework.jdbc

import org.junit.jupiter.api.Test
import org.springframework.context.support.ClassPathXmlApplicationContext

class Main {

	@Test
	void jdbcExample() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/jdbc/spring-jdbc-example.xml")
		def userService = context.getBean("userService", UserService)
		def user = new User(name: "张三", age: 20, sex: "男")
		userService.save(user)
		userService.getUsers().forEach(System.out::println)
	}

}
