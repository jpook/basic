package com.pook.feeding.presenter.interf;

import java.io.Serializable;
import java.util.Collection;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;

public interface IPresenter<T extends IEntity> extends Serializable {
	public boolean save(Object o);

	public void delete(Object o);

	public Collection<T> loadData();
	
	public T createNewEntity();
}
