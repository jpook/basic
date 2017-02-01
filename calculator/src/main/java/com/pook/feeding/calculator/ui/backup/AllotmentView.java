package com.pook.feeding.calculator.ui.backup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.pook.feeding.presenter.interf.IPresenter;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@SessionScoped
public class AllotmentView extends JpoAbstractView<Allotment> {

	@Inject
	AllotmentPresenter presenter;

	@Inject
	AllotmentForm form;

	@PostConstruct
	public void init() {
		table = new MTable<Allotment>(Allotment.class).withFullWidth()
				.withProperties("id", "name", "owner", "dateOfHarvest", "harvestWeight", "currentWeight")
				.withColumnHeaders("ID", "Name", "Owner", "Harvest Date", "Harvest Weight", "Current Weight")
				;
		table.setColumnCollapsingAllowed(true);
		initTable(); 
	}

	@Override
	protected IPresenter<Allotment> getPresenter() {
		return presenter;
	}

	@Override
	protected AbstractForm<Allotment> getForm() {
		return form;
	}

	protected void addGeneratedColumns() {
		
	}
	protected MHorizontalLayout getGeneratedColumn(Allotment allotment) {
		MHorizontalLayout layout = new MHorizontalLayout();
		Label first = new MLabel();
		try {
			
			System.out.println(allotment.getName());
			first.setValue(Double.toString(allotment.getNutrientValues().get(0).getValue()));
		} catch (Exception e) {
			System.out.println("NPE !");
		}
		return layout;
	}
	

}
