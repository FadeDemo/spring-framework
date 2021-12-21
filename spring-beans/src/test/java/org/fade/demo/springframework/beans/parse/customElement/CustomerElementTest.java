package org.fade.demo.springframework.beans.parse.customElement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class CustomerElementTest {

	@Test
	public void testCustomerElement() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/customerElement/customerElement.xml");
		User user = context.getBean("1", User.class);
		log.info(user.toString());
	}

}
