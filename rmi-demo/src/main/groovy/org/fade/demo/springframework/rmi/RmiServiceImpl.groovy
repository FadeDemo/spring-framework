package org.fade.demo.springframework.rmi

class RmiServiceImpl implements RmiService {

	@Override
	int add(int a, int b) {
		a + b
	}

}
