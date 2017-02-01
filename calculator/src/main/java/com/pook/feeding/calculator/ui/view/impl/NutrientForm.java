package com.pook.feeding.calculator.ui.view.impl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.pook.feeding.presenter.impl.NutrientUnitPresenter;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientForm extends AbstractForm<Nutrient> {

	@Inject
	NutrientPresenter presenter;

	@Inject
	NutrientUnitPresenter nutrientUnitPresenter;

	Header total = new Header("Nutrient Unit").setHeaderLevel(3);

	TextField name = new MTextField("Name");
	TypedSelect<NutrientUnit> nutrientUnit = new TypedSelect<NutrientUnit>();
	TextField orderNo = new MTextField("Order No");

	@Override
	protected Component createContent() {
		nutrientUnit.setImmediate(true);
		MVerticalLayout layout = new MVerticalLayout(total,
				 getToolbar(),
				nutrientUnit, name, orderNo);
		return layout;
	}

	@Override
	public Window openInModalPopup() {
		nutrientUnit.setOptions(nutrientUnitPresenter.loadData());
		final Window openInModalPopup = super.openInModalPopup();
		openInModalPopup.setWidth("400px");
		return openInModalPopup;
	}

}
