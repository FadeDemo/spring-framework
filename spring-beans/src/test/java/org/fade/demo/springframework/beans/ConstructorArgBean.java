package org.fade.demo.springframework.beans;

import lombok.ToString;

/**
 * @author fade
 * @date 2022/01/20
 */
// @Data不支持xml构造器注入？
//@Data
@ToString
public class ConstructorArgBean {

	private Long id;

	private String name;

	public ConstructorArgBean(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
