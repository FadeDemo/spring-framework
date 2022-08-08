package org.fade.demo.springframework.httpinvoker

/**
*   http调用程序服务impl
*
* @author fade
* @date 2022 /08/08
*/
class HttpInvokerServiceImpl implements HttpInvokerService{

	@Override
	String test(String arg) {
		"test: ${arg}"
	}

}
