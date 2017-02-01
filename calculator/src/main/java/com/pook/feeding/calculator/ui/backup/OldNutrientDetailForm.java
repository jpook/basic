package com.pook.feeding.calculator.ui.backup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SessionScoped
@SuppressWarnings("serial")
public class OldNutrientDetailForm extends CustomComponent {

	@Inject
	NutrientPresenter presenter;

	FormLayout formLayout;

	BeanFieldGroup<Nutrient> beanFieldGroup;
	BeanItemContainer<NutrientUnit> nutrientUnitContainer;
	BeanItem<Nutrient> nutrient = null;

	TextField name;
	ComboBox nutrientUnit;

	Integer id = new Integer(1);

	@PostConstruct
	public void init() {
		formLayout = new FormLayout();
		name = new TextField("Name");
		nutrientUnit = new ComboBox("Unit");
		nutrientUnit.setWidth("1500px");
		formLayout.addComponents(name, nutrientUnit);

		// create BeanFieldGroup and bind fields
		beanFieldGroup = new BeanFieldGroup<Nutrient>(Nutrient.class);
		beanFieldGroup.bindMemberFields(this);

		// create combo box with all nutrient units
		nutrientUnitContainer = new BeanItemContainer<NutrientUnit>(NutrientUnit.class, presenter.getNutrientUnits());
		nutrientUnit.setContainerDataSource(nutrientUnitContainer);
		nutrientUnit.setImmediate(true);

		// button to switch between 2 instances of Nutrient (id 1 or 2)
		Button b = new Button("Test");
		b.addClickListener(e -> loadExample());

		Button s = new Button("Save");
		s.addClickListener(e -> saveEntity());
		formLayout.addComponents(s, b);
		setCompositionRoot(formLayout);

	}

	private void saveEntity() {
		try {
			beanFieldGroup.commit();
			presenter.save(nutrient.getBean());
		} catch (CommitException e) {
			e.printStackTrace();
		}

	}

	// switches between Nutrient 1 and 2
	public void loadExample() {
		id++;
		nutrient = new BeanItem<Nutrient>(presenter.getEntityById((id % 2) + 1));
		beanFieldGroup.setItemDataSource(nutrient);
		try {
			beanFieldGroup.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
