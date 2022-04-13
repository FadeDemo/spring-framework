package org.fade.demo.springframework.aop

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class DemoInterceptor implements MethodInterceptor{

	@Override
	Object invoke(MethodInvocation invocation) throws Throwable {
		println "demo before"
		def ret = invocation.proceed()
		println "demo after"
		ret
	}

}
