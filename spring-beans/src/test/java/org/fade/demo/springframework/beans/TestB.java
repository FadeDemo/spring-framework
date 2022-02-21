package org.fade.demo.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestB {

	private TestC testC;

	public void b() {
		testC.c();
	}

}
