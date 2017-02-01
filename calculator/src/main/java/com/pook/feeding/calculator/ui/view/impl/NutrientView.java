package com.pook.feeding.calculator.ui.view.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.pook.feeding.presenter.interf.IPresenter;

@SuppressWarnings("serial")
@SessionScoped
public class NutrientView extends JpoAbstractView<Nutrient> {
	// TODO: handle dependency on delete
	@Inject
	NutrientPresenter presenter;

	@Inject
	NutrientForm form;

	@PostConstruct
	public void init() {
		table = new MTable<Nutrient>(Nutrient.class).withFullWidth().withProperties("id", "name", "nutrientUnit","orderNo")
				.withColumnHeaders("ID", "Name", "Unit Name","Order No");
		table.setColumnCollapsingAllowed(true);
		initTable();
	}

	@Override
	protected IPresenter<Nutrient> getPresenter() {
		return presenter;
	}

	@Override
	protected AbstractForm<Nutrient> getForm() {
		return form;
	}

}
