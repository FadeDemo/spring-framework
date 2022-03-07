package org.fade.demo.springframework.beans

class SimpleBean {

	String connectionString

	String password

	String username

	@Override
	String toString() {
		SimpleBean.name + this.properties
	}

}
