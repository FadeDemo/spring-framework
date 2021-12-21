package org.fade.demo.springframework.beans.parse.customElement;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class UserNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		/**
		 * 注册自定义的 {@link BeanDefinitionParser}
		 * */
		super.registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}

}
