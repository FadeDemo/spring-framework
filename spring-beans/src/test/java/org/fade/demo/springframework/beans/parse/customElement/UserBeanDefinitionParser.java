package org.fade.demo.springframework.beans.parse.customElement;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		// 解析username属性和email属性
		String username = element.getAttribute("username");
		String email = element.getAttribute("email");
		/**
		 * 将数据放至 {@link BeanDefinitionBuilder} ，待完成所有bean的解析后统一注册到 {@link org.springframework.beans.factory.BeanFactory} 中
		 * */
		if (StringUtils.hasText(username)) {
			builder.addPropertyValue("username", username);
		}
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}
	}

}
