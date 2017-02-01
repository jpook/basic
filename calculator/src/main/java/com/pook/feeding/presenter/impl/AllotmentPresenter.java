package com.pook.feeding.presenter.impl;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.dao.impl.AllotmentModel;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;
import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.ui.view.impl.AllotmentView;

@SuppressWarnings("serial")
@SessionScoped
public class AllotmentPresenter extends APresenter<Allotment> {

	AllotmentView view;

	@Inject
	AllotmentModel model;

	public void init() {
	}

	public AllotmentView getView() {
		return view;
	}

	public void setView(AllotmentView view) {
		this.view = view;
	}

	@Override
	protected AllotmentModel getModel() {
		return model;
	}

	@Override
	protected Class<Allotment> getClazz() {
		return Allotment.class;
	}

	public List<Nutrient> getNutrients() {
		return null;
	}

	public NutrientValue getValueByNutrient(Allotment a, Nutrient n) {
		return model.getValueByNutrient(a, n);
	}

	public Allotment createNewEntity() {
		Allotment a = getModel().create();
		List<Nutrient> nutrients = model.getMandatoryNutrients();
		if (nutrients != null) {
			for (int i = 0; i < nutrients.size(); i++) {
				getValueByNutrient(a, nutrients.get(i));
			}
		}
		return a;
	}

}
