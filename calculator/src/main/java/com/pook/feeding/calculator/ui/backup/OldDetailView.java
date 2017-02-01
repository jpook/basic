package com.pook.feeding.calculator.ui.backup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.ui.view.abstr.DetailViewAbstr;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldDetailView")
public class OldDetailView extends DetailViewAbstr<Allotment> {

	@Inject
	AllotmentPresenter presenter;

	// BeanItemContainer<Owner> ownerContainer;

	TextField name;
	DateField dateOfHarvest;
	TextField harvestWeight;
	TextField currentWeight;

	// @PropertyId("nutrientUnit")
	// ComboBox nutrientUnit;

	@Override
	protected BeanFieldGroup<Allotment> createBeanFieldGroup() {
		return new BeanFieldGroup<Allotment>(Allotment.class);
	}

	@Override
	protected void doBindings(Layout formLayout) {
		name = new TextField("Name");
		dateOfHarvest = new DateField("Harvest Date");
		harvestWeight = new TextField("Harvest Weight in kg");
		currentWeight = new TextField("Current Weight in kg");

		// nutrientUnit = new ComboBox("Unit");
		// nutrientUnit.setNullSelectionAllowed(false);
		// nutrientUnit.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		// nutrientUnit.setItemCaptionPropertyId("unitName");
		// formLayout.addComponents(name, nutrientUnit);
		// nutrientUnit.setImmediate(true);
		//
		// beanFieldGroup = new BeanFieldGroup<Allotment>(Allotment.class);
		// beanFieldGroup.setItemDataSource(beanItem.getBeanItem());

		// nutrientUnitContainer = new
		// BeanItemContainer<AllotmentUnit>(AllotmentUnit.class,
		// presenter.getAllotmentUnits());
		// nutrientUnit.setContainerDataSource(nutrientUnitContainer);

		formLayout.addComponents(name, dateOfHarvest, harvestWeight, currentWeight);

	}

	@Override
	protected void reloadData() {
		// TODO Auto-generated method stub
		
	}

}
