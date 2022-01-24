package org.fade.demo.springframework.beans;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author fade
 * @date 2022/01/24
 */
public class CustomElementNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		CustomElementBeanDefinitionParser customElementBeanDefinitionParser = new CustomElementBeanDefinitionParser();
		registerBeanDefinitionParser("custom-element", customElementBeanDefinitionParser);
	}

}
