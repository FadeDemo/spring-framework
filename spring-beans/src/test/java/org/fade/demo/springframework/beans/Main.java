package org.fade.demo.springframework.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fade
 * @date 2022/01/05
 */
public class Main {

	@Test
	public void main() {
		ApplicationContext context = new ClassPathXmlApplicationContext("org/fade/demo/springframework/beans/getBeanTest.xml");
		GetBeanTest bean = context.getBean("getBeanTest", GetBeanTest.class);
		bean.showMe();
	}

}
