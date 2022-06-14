package org.fade.demo.springframework.mvc

import org.springframework.lang.Nullable
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyHandlerInterceptor implements HandlerInterceptor {

	@Override
	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis())
		super.preHandle(request, response, handler)
	}

	@Override
	void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		def end = System.currentTimeMillis()
		def start = request.getAttribute("startTime")
		def handleTime = end - start
		modelAndView.addObject("handleTime", handleTime)
	}

}
