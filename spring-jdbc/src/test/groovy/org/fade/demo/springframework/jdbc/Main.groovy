package org.fade.demo.springframework.jdbc

import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.fade.demo.springframework.jdbc.mybatis.UserMapper
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

	@Test
	void testSaveThrowException() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/jdbc/spring-jdbc-example.xml")
		def userService = context.getBean("userService", UserService)
		def user = new User(name: "张三", age: 20, sex: "男")
		userService.saveThrowException(user)
		userService.getUsers().forEach(System.out::println)
	}

	@Test
	void testSaveWithoutArrayIndexOutOfBound() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/jdbc/spring-jdbc-example.xml")
		def userService = context.getBean("userService", UserService)
		def user = new User(name: "张三", age: 20, sex: "男")
		userService.saveWithoutArrayIndexOutOfBound(user)
		userService.getUsers().forEach(System.out::println)
	}

	@Test
	void mybatisExample() {
		final def resource = "org/fade/demo/springframework/jdbc/mybatis-config.xml"
		def reader = Resources.getResourceAsReader(resource)
		def sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader)
		def sqlSession = sqlSessionFactory.openSession()
		def userMapper = sqlSession.getMapper(UserMapper)
		def user = new User(name: "李四", age: 21, sex: "女")
		userMapper.insertUser(user)
		sqlSession.commit()
		def data = userMapper.getUser(user.getId())
		if (data) {
			println data
		}
		sqlSession.close()
	}

	@Test
	void springMybatisExample() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/jdbc/spring-mybatis.xml")
		def userMapper = context.getBean("userMapper", UserMapper)
		def user = userMapper.getUser(32)
		if (user) {
			println user
		}
	}

	@Test
	void springMybatisWithMapperScannerConfigurer() {
		def context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/jdbc/spring-mybatis-withMapperScannerConfigurer.xml")
		def userMapper = context.getBean("userMapper", UserMapper)
		def user = userMapper.getUser(32)
		if (user) {
			println user
		}
	}

}
