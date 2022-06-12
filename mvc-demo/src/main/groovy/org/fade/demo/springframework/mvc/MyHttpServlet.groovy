package org.fade.demo.springframework.mvc

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyHttpServlet extends HttpServlet {

	@Override
	void init() throws ServletException {
		super.init()
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleLogin(req, resp)
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleLogin(req, resp)
	}

	private void handleLogin(HttpServletRequest req, HttpServletResponse resp) {
		log("custom handle login logic")
		def context = servletContext
		def dispatcher = context.getRequestDispatcher("/index.jsp")
		dispatcher.forward(req, resp)
	}

}
