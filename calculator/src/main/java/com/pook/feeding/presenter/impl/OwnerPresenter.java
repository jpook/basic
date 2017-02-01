package com.pook.feeding.presenter.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import com.pook.feeding.calculator.model.dao.abstr.AModel;
import com.pook.feeding.calculator.model.db.entities.impl.Owner;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;

@SuppressWarnings("serial")
@SessionScoped
public class OwnerPresenter extends APresenter<Owner>{

	@PostConstruct
	public void init() {
	}

	@Override
	protected AModel<Owner> getModel() {
		return null;
	}


	@Override
	protected Class<Owner> getClazz() {
		return Owner.class;
	}

	@Override
	protected JpoAbstractView<Owner> getView() {
		return null;
	}
	
}
