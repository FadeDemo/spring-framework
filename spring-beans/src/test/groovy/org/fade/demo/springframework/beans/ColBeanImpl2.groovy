package org.fade.demo.springframework.beans

import org.jetbrains.annotations.NotNull
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Order(Ordered.HIGHEST_PRECEDENCE)
//class ColBeanImpl2 implements ColBean, Ordered{
class ColBeanImpl2 implements ColBean {

	@Override
	void test() {
		println "hello 2"
	}

//	@Override
//	int compareTo(@NotNull Object o) {
//		return 0
//	}

//	@Override
//	int getOrder() {
//		return Ordered.HIGHEST_PRECEDENCE
//	}

}
