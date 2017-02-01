package com.pook.feeding.events;

public class DeleteEntityEvent {

	private Object object;
	
	public DeleteEntityEvent(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
