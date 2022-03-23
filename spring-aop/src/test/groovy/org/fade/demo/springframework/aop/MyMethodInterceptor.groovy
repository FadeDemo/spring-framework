package org.fade.demo.springframework.aop

import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy

import java.lang.reflect.Method

class MyMethodInterceptor implements MethodInterceptor {

	@Override
	Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		println "before"
		def result = methodProxy.invokeSuper(o, objects)
		println "after"
		result
	}

}
