package org.fade.demo.springframework.mvc

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.AbstractController
import org.springframework.web.servlet.mvc.LastModified

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LastModifiedController extends AbstractController implements LastModified{

	private long lastModified

	@Override
	long getLastModified(HttpServletRequest request) {
		if (this.lastModified == 0) {
			lastModified = System.currentTimeMillis()
		}
		return lastModified
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		println "test LastModified"
		response.writer.write("Hello, LastModified")
		return null
	}

}
