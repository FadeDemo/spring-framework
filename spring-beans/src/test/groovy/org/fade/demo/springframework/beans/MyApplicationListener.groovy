package org.fade.demo.springframework.beans

import org.springframework.context.ApplicationListener

class MyApplicationListener<E> implements ApplicationListener<E> {

	@Override
	void onApplicationEvent(E event) {
		if (event instanceof TestEvent) {
			event.print()
		}
	}

}
