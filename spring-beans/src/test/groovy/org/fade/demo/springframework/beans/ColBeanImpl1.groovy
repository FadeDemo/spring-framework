package org.fade.demo.springframework.beans

import org.jetbrains.annotations.NotNull
import org.springframework.core.Ordered

class ColBeanImpl1 implements ColBean, Ordered{

	@Override
	void test() {
		println "hello 1"
	}

//	@Override
//	int compareTo(@NotNull Object o) {
//		return 0
//	}

	@Override
	int getOrder() {
		Ordered.LOWEST_PRECEDENCE
	}

}
