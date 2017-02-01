package com.pook.feeding.calculator.ui.items;

import java.io.Serializable;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;
import com.vaadin.data.util.BeanItem;

@SuppressWarnings("serial")
public abstract class EntityBeanItem<T extends IEntity> implements Serializable {

	private BeanItem<T> beanItem;

	public EntityBeanItem(Class<T> clazz) {
		beanItem = new BeanItem<T>(null, clazz);
	}

	public BeanItem<T> getBeanItem() {
		return beanItem;
	}

	public void setBeanItem(BeanItem<T> beanItem) {
		this.beanItem = beanItem;
	}

}
