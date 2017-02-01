package com.pook.feeding.presenter.impl;

import java.util.Collection;

import com.pook.feeding.calculator.model.dao.abstr.AModel;
import com.pook.feeding.calculator.model.db.entities.interf.IEntity;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.interf.IPresenter;

@SuppressWarnings("serial")
public abstract class APresenter<T extends IEntity> implements IPresenter<T> {

	public APresenter() {
		super();
	}
	
	protected abstract AModel<T> getModel();
//	protected abstract ListViewAbstr<T> getView();
	protected abstract JpoAbstractView<T> getView();
	protected abstract Class<T> getClazz();

	public Collection<T> loadData() {
		return getModel().getResultList();
	}

	public boolean save(Object o) {
		boolean result = getModel().saveEntity(o);
		return result;
	}

	public void delete(Object o) {
		getModel().deleteEntity(o);
	}
	
	public T getEntityById(Object id) {
		return getModel().getEntityById(getClazz(), (Integer) id);
	}
	
	public T createNewEntity() {
		return getModel().create();
	}

}