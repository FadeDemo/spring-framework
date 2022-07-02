package org.fade.demo.springframework.rmi

import java.rmi.Remote
import java.rmi.RemoteException

/**
 * <p>必须实现 {@link Remote} 接口
 * */
interface JdkRmiService extends Remote {

	/**
	 * <p>必须抛出 {@link RemoteException}
	 * */
	int add(int a, int b) throws RemoteException

}