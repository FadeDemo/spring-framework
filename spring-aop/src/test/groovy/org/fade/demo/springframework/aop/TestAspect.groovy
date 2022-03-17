package org.fade.demo.springframework.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

@Aspect
class TestAspect {

	@Pointcut("execution(* *.test(..))")
	void test() {

	}

	@Before("test()")
	def beforeTest() {
		println "before test"
	}

	@After("test()")
	def afterTest() {
		println "after test"
	}

	@Around("test()")
	def aroundTest(ProceedingJoinPoint proceedingJoinPoint) {
		println "around before test"
		def retVal = proceedingJoinPoint.proceed()
		println "around after test"
		retVal
	}

}
