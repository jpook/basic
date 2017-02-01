package com.pook.feeding.calculator.ui.view.impl;

import java.text.SimpleDateFormat;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;
import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@SessionScoped
public class AllotmentForm extends AbstractForm<Allotment> {
	// TODO: add fields for name, etc
	// TODO: add validation
	// TODO: set column headers

	@Inject
	AllotmentPresenter allotmentPresenter;

	@Inject
	NutrientPresenter nutrientPresenter;

	@Inject
	ValidatorFacade validatorFacade;

	Header total = new Header("Nutrients").setHeaderLevel(3);

	Table table = new MTable<NutrientValue>(NutrientValue.class).withFullWidth();
	BeanItemContainer<NutrientValue> bic = new BeanItemContainer<NutrientValue>(NutrientValue.class);
	Header h1 = new Header("").setHeaderLevel(2);
	Header h2 = new Header("").setHeaderLevel(2);

	TextField name = new MTextField("Name").withValidator(new NullValidator("may not be empty", false));
	DateField dateOfHarvest = new MDateField();
	TextField harvestWeight = new MTextField("Harvest Weight");
	TextField currentWeight = new MTextField("Current Weight");

	@Override
	protected Component createContent() {

		bic.addNestedContainerBean("nutrient");

		table.setContainerDataSource(bic);
		table.setVisibleColumns("nutrient.orderNo", "nutrient.name", "value", "nutrient.nutrientUnitName");
		table.setEditable(true);
		table.setTableFieldFactory(getFieldFactory());

		MVerticalLayout layout = new MVerticalLayout(h1, h2, total, getToolbar(), name, dateOfHarvest, harvestWeight,
				currentWeight, table);

		return layout;
	}

	private void valueChange(ValueChangeEvent e) {
		getSaveButton().setEnabled(true);
	}

	private TableFieldFactory getFieldFactory() {
		return new DefaultFieldFactory() {

			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

				if ((propertyId == "value")) {
					Field<?> f = super.createField(container, itemId, propertyId, uiContext);
					if (f instanceof TextField) {
						((TextField) f).setEnabled(true);
						f.addValueChangeListener(e -> valueChange(e));
						Nutrient n = ((NutrientValue) itemId).getNutrient();
						String validatorName = n.getValidator();
						Validator validator = validatorFacade.findValidator(validatorName, n.getMinimal(),
								n.getMaximal(), n.getFormreg());
						if (validator != null)
							f.addValidator(validator);

						// Double min = presenter.getMin()
						// TODO: Validator erstellen
						return f;
					}
				}
				return null;
			}
		};
	}

	@Override
	public Window openInModalPopup() {
		update();
		h1.setValue(getEntity().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		h2.setValue(sdf.format(getEntity().getDateOfHarvest()));
		final Window openInModalPopup = super.openInModalPopup();
		openInModalPopup.setWidth("1200px");
		return openInModalPopup;
	}

	public void update() {
		bic.removeAllItems();
		bic.addAll(getEntity().getNutrientValues());
	}

}
