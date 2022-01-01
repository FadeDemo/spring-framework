package org.fade.demo.springframework.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 *
 * @author fade
 * @date 2021/12/23
 */
public class TestCase {

	@Test
	public void main() {
		// --add-opens java.base/java.lang=ALL-UNNAMED
//		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/transaction/applicationContext.xml");
		UserService userService = context.getBean("userServiceImpl", UserService.class);
		User user = new User();
		user.setName("张三");
		user.setAge(18);
		user.setSex("男");
		userService.save(user);
	}

}
