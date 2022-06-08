package org.fade.demo.springframework.mvc

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.AbstractController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		def userList = new ArrayList<User>(16)
		def a = new User(username: "张三", age: 25)
		def b = new User(username: "李四", age: 29)
		userList.add(a)
		userList.add(b)
		def data = request.servletContext.getAttribute("data")
		logger.info("data from customize ServletContextListener: " + data)
		return new ModelAndView("userList", "users", userList)
	}

}
