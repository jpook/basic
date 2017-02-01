package com.pook.feeding.calculator.ui.view.impl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.presenter.impl.NutrientUnitPresenter;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientUnitForm extends AbstractForm<NutrientUnit> {

	@Inject
	NutrientUnitPresenter nutrientUnitPresenter;

	Header total = new Header("Nutrient Unit").setHeaderLevel(3);

	TextField unitName = new MTextField("Unit Name");

	@Override
	protected Component createContent() {
		MVerticalLayout layout = new MVerticalLayout(total,
				 getToolbar(),
				unitName);
		return layout;
	}

	@Override
	public Window openInModalPopup() {
			final Window openInModalPopup = super.openInModalPopup();
		openInModalPopup.setWidth("400px");
		return openInModalPopup;
	}

}
