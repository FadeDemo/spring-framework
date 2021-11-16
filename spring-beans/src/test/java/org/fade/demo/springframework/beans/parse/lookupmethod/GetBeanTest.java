package org.fade.demo.springframework.beans.parse.lookupmethod;

/**
 * @author fade
 * @date 2021/11/16
 */
public abstract class GetBeanTest {

	public void showMe() {
		this.getBean().showMe();
	}

	/**
	 * 获取bean
	 * @return see {@link User}
	 * */
	public abstract User getBean();

}
