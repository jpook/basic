package com.pook.feeding.calculator.ui.backup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.abstr.ListViewAbstr;
import com.pook.feeding.presenter.impl.NutrientUnitPresenter;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldNutrientUnitView")
public class OldNutrientUnitView extends ListViewAbstr<NutrientUnit> {

	@Inject
	NutrientUnitPresenter presenter;

	@PostConstruct
	public void init() {
//		presenter.setView(this);
	}

	@Override
	protected void setBeanItemContainer() {
		beanContainer = new BeanItemContainer<NutrientUnit>(NutrientUnit.class);
	}

	@Override
	protected void createTable() {
		table.setVisibleColumns((Object[]) new String[] { "id", "unitName" });
		table.setColumnHeaders(new String[] { "ID", "Unit Name" });
	}

	public NutrientUnitPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(NutrientUnitPresenter presenter) {
		this.presenter = presenter;
	}

}
