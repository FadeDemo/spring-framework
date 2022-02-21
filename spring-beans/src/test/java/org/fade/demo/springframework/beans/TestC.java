package org.fade.demo.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestC {

	private TestA testA;

	public void c() {
		testA.a();
	}

}
