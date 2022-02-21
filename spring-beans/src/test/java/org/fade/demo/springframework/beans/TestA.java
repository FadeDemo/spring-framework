package org.fade.demo.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestA {

	private TestB testB;

	public void a() {
		testB.b();
	}

}
