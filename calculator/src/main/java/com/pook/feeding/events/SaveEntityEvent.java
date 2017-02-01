package com.pook.feeding.events;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;

public class SaveEntityEvent<T extends IEntity> {

	private Object object;
	
	public SaveEntityEvent(Object object) {
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
