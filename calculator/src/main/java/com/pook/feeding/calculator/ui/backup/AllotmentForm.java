package com.pook.feeding.calculator.ui.backup;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.Header;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.model.db.entities.impl.Owner;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.pook.feeding.presenter.impl.OwnerPresenter;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@SessionScoped
public class AllotmentForm extends AbstractForm<Allotment> {

	@Inject
	AllotmentPresenter allotmentPresenter;

	@Inject
	OwnerPresenter ownerPresenter;

	Header total = new Header("Allotment").setHeaderLevel(3);

	TextField name = new MTextField("Name");
	TypedSelect<Owner> owner = new TypedSelect<Owner>();
	DateField dateOfHarvest = new MDateField("Harvest Date");
	TextField harvestWeight = new MTextField("Harvest Weight");
	TextField currentWeight = new MTextField("Current Weight");

	@Override
	protected Component createContent() {
		owner.setImmediate(true);
		owner.setVisible(true);
		MVerticalLayout layout = new MVerticalLayout(total, getToolbar(), name, owner, dateOfHarvest, harvestWeight,
				currentWeight);
		return layout;
	}

	@Override
	public Window openInModalPopup() {
		// owner.setOptions(ownerPresenter.loadData());
		final Window openInModalPopup = super.openInModalPopup();
		openInModalPopup.setWidth("400px");
		return openInModalPopup;
	}

}
