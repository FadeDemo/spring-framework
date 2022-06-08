package org.fade.demo.springframework.mvc

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class MyServletContextListener implements ServletContextListener {

	ServletContext servletContext

	@Override
	void contextInitialized(ServletContextEvent sce) {
		this.servletContext = sce.servletContext
		this.servletContext.setAttribute("data", "hello world")
	}

	@Override
	void contextDestroyed(ServletContextEvent sce) {
		this.servletContext = null
	}

}
