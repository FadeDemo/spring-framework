package org.fade.demo.springframework.httpinvoker

/**
*   http调用程序服务
*
* @author fade
* @date 2022 /08/08
*/
interface HttpInvokerService {

	/**
	 * <p>测试</p>
	 * @param arg {@link String}
	 * @return {@link String}
	 * */
	String test(String arg);

}