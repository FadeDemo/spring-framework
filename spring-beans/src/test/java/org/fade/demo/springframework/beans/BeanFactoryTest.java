package org.fade.demo.springframework.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * bean工厂测试
 *
 * @author fade
 * @date 2022/01/03
 */
public class BeanFactoryTest {

	@Test
	@SuppressWarnings("deprecation")
	public void testSimpleLoad() {
		final String str = "testStr";
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("org/fade/demo/springframework/beans/beanFactoryTest.xml"));
		MyTestBean bean = bf.getBean("myTestBean", MyTestBean.class);
		assert bean != null;
		assert str.equals(bean.getTestStr());
	}

}
