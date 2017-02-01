package com.pook.feeding.calculator.ui.view.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm;

import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.impl.NutrientUnitPresenter;
import com.pook.feeding.presenter.interf.IPresenter;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientUnitView extends JpoAbstractView<NutrientUnit> {

	@Inject
	NutrientUnitPresenter presenter;

	@Inject
	NutrientUnitForm form;

	@PostConstruct
	public void init() {
		table = new MTable<NutrientUnit>(NutrientUnit.class).withFullWidth()
				.withProperties("id", "unitName").withColumnHeaders("ID", "Unit Name");
		table.setColumnCollapsingAllowed(true);
		initTable();
	}

	@Override
	protected IPresenter<NutrientUnit> getPresenter() {
		return presenter;
	}

	@Override
	protected AbstractForm<NutrientUnit> getForm() {
		return form;
	}

}
