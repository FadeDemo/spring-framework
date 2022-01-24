package org.fade.demo.springframework.beans;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author fade
 * @date 2022/01/24
 */
public class CustomElementBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	private static final String ATTRIBUTE_ID = "id";

	private static final String ATTRIBUTE_USERNAME = "username";

	private static final String ATTRIBUTE_EMAIL = "email";

	@Override
	protected void doParse(@NotNull Element element, @NotNull BeanDefinitionBuilder builder) {
		String id = element.getAttribute(ATTRIBUTE_ID);
		String username = element.getAttribute(ATTRIBUTE_USERNAME);
		String email = element.getAttribute(ATTRIBUTE_EMAIL);
		if (StringUtils.hasText(id)) {
			builder.addPropertyValue("id", Long.valueOf(id));
		}
		if (StringUtils.hasText(username)) {
			builder.addPropertyValue("username", username);
		}
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}
	}

	@Override
	protected Class<?> getBeanClass(@NotNull Element element) {
		return CustomElementBean.class;
	}

}
