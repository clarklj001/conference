package com.prodyna.conference.event;

public class HelloEvent {

	private String string;


	public String getString() {
		return string;
	}

	public HelloEvent(String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return "HelloEvent [string=" + string + "]";
	}
}
