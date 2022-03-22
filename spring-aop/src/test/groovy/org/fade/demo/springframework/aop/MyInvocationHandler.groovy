package org.fade.demo.springframework.aop

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class MyInvocationHandler implements InvocationHandler{

	Object target;

	MyInvocationHandler(Object target) {
		this.target = target
	}

	@Override
	Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		println "************before************"
		def retVal = method.invoke(target, args)
		println "************after************"
		retVal
	}

	def getProxy() {
		Proxy.newProxyInstance(Thread.currentThread().contextClassLoader, target.class.interfaces, this)
	}

}
