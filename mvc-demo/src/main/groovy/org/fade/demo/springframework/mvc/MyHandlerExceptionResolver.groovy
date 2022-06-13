package org.fade.demo.springframework.mvc

import org.springframework.lang.Nullable
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
		request.setAttribute("exception", ex.toString())
		request.setAttribute("exceptionStack", ex)
		System.err.println(ex)
		return new ModelAndView("500")
	}

}
