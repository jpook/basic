package com.pook.feeding.calculator.ui.backup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.abstr.DetailViewAbstr;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldNutrientUnitDetailView")
public class OldNutrientUnitDetailView extends DetailViewAbstr<NutrientUnit> {

	@Override
	protected BeanFieldGroup<NutrientUnit> createBeanFieldGroup() {
		return new BeanFieldGroup<NutrientUnit>(NutrientUnit.class);
	}

	@Override
	protected void doBindings(Layout formLayout) {
		formLayout.addComponent(beanFieldGroup.buildAndBind("ID", "id"));
		formLayout.addComponent(beanFieldGroup.buildAndBind("Unit Name", "unitName"));
	}

	@Override
	protected void reloadData() {
	}

}
