package org.fade.demo.springframework.rmi

import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject

class JdkRmiServiceImpl extends UnicastRemoteObject implements JdkRmiService {

	@Override
	int add(int a, int b) throws RemoteException {
		a + b
	}

}
