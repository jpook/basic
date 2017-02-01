package com.pook.feeding.calculator.ui.backup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.ui.view.abstr.ListViewAbstr;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldAllotmentView")
public class OldAllotmentView extends ListViewAbstr<Allotment> {

	@Inject
	AllotmentPresenter presenter;

	@PostConstruct
	public void init() {
//		presenter.setView(this);
	}

	@Override
	protected void setBeanItemContainer() {
		beanContainer =  new BeanItemContainer<Allotment>(Allotment.class);
//		beanContainer.addNestedContainerProperty("nutrientUnit.unitName");
	}

	@Override
	protected void createTable() {
		table.setVisibleColumns((Object[]) new String[] { "id", "name","owner","dateOfHarvest", "harvestWeight", "currentWeight" });
		table.setColumnHeaders(new String[] { "ID", "Name","Owner", "Harvest Date", "Harvest Weight", "Current Weight" });
		
	}

	@Override
	public AllotmentPresenter getPresenter() {
		return presenter;
	}

}
