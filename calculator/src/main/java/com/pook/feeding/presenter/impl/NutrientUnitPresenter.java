package com.pook.feeding.presenter.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.dao.impl.NutrientUnitModel;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.impl.NutrientUnitView;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientUnitPresenter extends APresenter<NutrientUnit> {

	NutrientUnitView view;

	@Inject
	NutrientUnitModel model;

	@PostConstruct
	public void init() {
	}

	public NutrientUnitView getView() {
		return view;
	}

	public void setView(NutrientUnitView view) {
		this.view = view;
	}

	@Override
	protected NutrientUnitModel getModel() {
		return model;
	}

	@Override
	protected Class<NutrientUnit> getClazz() {
		return NutrientUnit.class;
	}

}
