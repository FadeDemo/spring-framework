package org.fade.demo.springframework.beans

import org.springframework.context.ApplicationEvent

import java.time.Clock

class TestEvent extends ApplicationEvent {

	String message

	TestEvent(Object source) {
		super(source)
	}

	TestEvent(Object source, Clock clock) {
		super(source, clock)
	}

	void print() {
		println message
	}

}
