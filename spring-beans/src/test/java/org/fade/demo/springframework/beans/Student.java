package org.fade.demo.springframework.beans;

/**
 * 学生
 *
 * @author fade
 * @date 2022/01/05
 */
public class Student extends User {

	@Override
	public void showMe() {
		System.out.println("I am a student");
	}

}
