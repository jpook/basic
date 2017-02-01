package com.pook.feeding.presenter.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.dao.impl.NutrientModel;
import com.pook.feeding.calculator.model.dao.impl.NutrientUnitModel;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.impl.NutrientView;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientPresenter extends APresenter<Nutrient>{

	NutrientView view;

	@Inject
	NutrientModel model;
	
	@Inject
	NutrientUnitModel nutrientUnitModel;

	@PostConstruct
	public void init() {
	}

	public NutrientView getView() {
		return view;
	}

	public void setView(NutrientView view) {
		this.view = view;
	}

	@Override
	protected NutrientModel getModel() {
		return model;
	}

	public Collection<? extends NutrientUnit> getNutrientUnits() {
		return nutrientUnitModel.getResultList();
	}

	@Override
	protected Class<Nutrient> getClazz() {
		return Nutrient.class;
	}
	public List<Nutrient> getShownNutrients() {
		return (List<Nutrient>) loadData();
	}
	
}
