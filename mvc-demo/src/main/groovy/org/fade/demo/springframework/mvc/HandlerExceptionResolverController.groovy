package org.fade.demo.springframework.mvc

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.AbstractController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HandlerExceptionResolverController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		throw new RuntimeException("test HandlerExceptionResolver")
		return null
	}

}
