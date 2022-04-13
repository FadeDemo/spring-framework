package org.fade.demo.springframework.aop

import org.springframework.aop.support.StaticMethodMatcherPointcut

import java.lang.reflect.Method

class DemoPointcut extends StaticMethodMatcherPointcut {

	@Override
	boolean matches(Method method, Class<?> targetClass) {
		method.getAnnotation(DemoAnnotation)
	}

}
