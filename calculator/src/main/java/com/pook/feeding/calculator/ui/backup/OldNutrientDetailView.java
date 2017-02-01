package com.pook.feeding.calculator.ui.backup;

import java.util.Collection;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.calculator.ui.view.abstr.DetailViewAbstr;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldNutrientDetailView")
public class OldNutrientDetailView extends DetailViewAbstr<Nutrient> {

	@Inject
	NutrientPresenter presenter;

	BeanItemContainer<NutrientUnit> nutrientUnitContainer;

	TextField name;

	@PropertyId("nutrientUnit")
	ComboBox nutrientUnit;

	@Override
	protected BeanFieldGroup<Nutrient> createBeanFieldGroup() {
		return new BeanFieldGroup<Nutrient>(Nutrient.class);
	}

	@Override
	protected void doBindings(Layout formLayout) {
		name = new TextField("Name");

		nutrientUnit = new ComboBox("Unit");
		nutrientUnit.setNullSelectionAllowed(false);

		formLayout.addComponents(name, nutrientUnit);
		nutrientUnit.setImmediate(true);

		nutrientUnitContainer = new BeanItemContainer<NutrientUnit>(NutrientUnit.class, presenter.getNutrientUnits());
		nutrientUnit.setContainerDataSource(nutrientUnitContainer);

	}
	
	@Override
	protected void reloadData() {
		System.out.println("reloadData() OldNutrientDetailView");
		Collection<NutrientUnit> c = (Collection<NutrientUnit>) presenter.getNutrientUnits();
		if (c!=null) {
			for (NutrientUnit o : c)
				System.out.println(o.getUnitName());
		}
		nutrientUnitContainer = new BeanItemContainer<NutrientUnit>(NutrientUnit.class, presenter.getNutrientUnits());
		nutrientUnit.setContainerDataSource(nutrientUnitContainer);
	}
	

}
