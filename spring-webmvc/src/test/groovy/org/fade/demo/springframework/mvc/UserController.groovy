package org.fade.demo.springframework.mvc

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.AbstractController

class UserController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		def userList = new ArrayList<User>(16)
		def a = new User(username: "张三", 25)
		def b = new User(username: "李四", 29)
		userList.add(a)
		userList.add(b)
		return new ModelAndView("userList", "users", userList)
	}

}
