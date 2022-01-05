package org.fade.demo.springframework.beans;

public class Teacher extends User {

	@Override
	public void showMe() {
		System.out.println("I am a teacher");
	}

}
