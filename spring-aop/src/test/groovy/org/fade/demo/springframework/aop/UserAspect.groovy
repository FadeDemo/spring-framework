package org.fade.demo.springframework.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class UserAspect {

	@Around("execution(* *.add(..))")
	Object invoke(ProceedingJoinPoint proceedingJoinPoint) {
		println "around before"
		def retVal = proceedingJoinPoint.proceed()
		println "around after"
		retVal
	}

}
