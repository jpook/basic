package com.pook.feeding.calculator.ui.view.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.vaadin.viritin.form.AbstractForm;

import com.pook.feeding.calculator.events.TabSheetChangedEvent;
import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.impl.AllotmentPresenter;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.pook.feeding.presenter.interf.IPresenter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
@SessionScoped
public class AllotmentView extends JpoAbstractView<Allotment> {
	// TODO: add validation
	// TODO: set column headers
	
	Grid grid = new Grid();

	@Inject
	AllotmentPresenter presenter;

	@Inject
	NutrientPresenter nutrientPresenter;

	@Inject
	AllotmentForm form;

	IndexedContainer c = new IndexedContainer();

	@PostConstruct
	public void init() {
		addButton = new Button("Add");
		addButton.addClickListener(e -> create());
		add(addButton);
		createContainer();
		update();

		grid.setContainerDataSource(c);
		grid.setSizeFull();
		grid.addSelectionListener(e -> selectValue(e));
		expand(grid);
		add(grid);
		form.setSavedHandler(e -> save(e));
		form.setResetHandler(e -> reset(e));
		form.setDeleteHandler(e -> delete(e));

		this.addLayoutClickListener(new LayoutClickListener() {

			@Override
			public void layoutClick(LayoutClickEvent event) {
				System.out.println("Layout clicked");
			}
		});
	}

	private void selectValue(SelectionEvent e) {
		Allotment a = (Allotment) ((SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();

		if (a != null) {
			System.out.println("selected : " + a.getName());
			form.setEntity(a);
			form.openInModalPopup();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected IndexedContainer createContainer() {
		ArrayList al =  new ArrayList(c.getContainerPropertyIds());
		if (al != null) {
			for (int i = 0; i < al.size();i++) {
				c.removeContainerProperty(al.get(i));
			}
		}
		
		c.addContainerProperty("name", String.class, "");
		c.addContainerProperty("dateOfHarvest", Date.class, null);
		c.addContainerProperty("harvestWeight", Double.class, 0.0);
		c.addContainerProperty("currentWeight", Double.class, 0.0);
		List<Nutrient> nutrients = nutrientPresenter.getShownNutrients();
		if (nutrients != null) {
			for (int k = 0; k < nutrients.size(); k++) {
				Nutrient nutrient = nutrients.get(k);
				c.addContainerProperty("nutrients." + nutrient.getName() + "_" + nutrient.getId(), Double.class, 0.0);
			}
		}

		return c;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fillWithData(List<Nutrient> nutrients, List<Allotment> allotments) {
		if (allotments != null) {
			for (int i = 0; i < allotments.size(); i++) {
				Allotment a = allotments.get(i);
				c.addItem(a);
				Item newItem = c.getItem(a);
				newItem.getItemProperty("name").setValue(a.getName());
				newItem.getItemProperty("dateOfHarvest").setValue(a.getDateOfHarvest());
				newItem.getItemProperty("harvestWeight").setValue(a.getHarvestWeight());
				newItem.getItemProperty("currentWeight").setValue(a.getCurrentWeight());
				for (int r = 0; r < nutrients.size(); r++) {
					Nutrient nutrient = nutrients.get(r);
					NutrientValue nv = presenter.getValueByNutrient(a, nutrient);

					if (nv != null) {
						Property p = newItem
								.getItemProperty("nutrients." + nutrient.getName() + "_" + nutrient.getId());
						p.setValue(nv.getValue());
					} 
				}
			}
		}
	}

	protected void save(Allotment entity) {
		getPresenter().save(entity);
		form.getPopup().close();
		update();
		Notification.show("Saved");
	}

	protected void delete(Allotment entity) {
		getPresenter().delete(entity);
		form.getPopup().close();
		update();
		Notification.show("Deleted");
	}

	protected void reset(Allotment entity) {
		getForm().setEntity(null);
		getForm().getPopup().close();
	}

	private void update() {
		c.removeAllItems();
		List<Allotment> allotments = (List<Allotment>) presenter.loadData();
		List<Nutrient> nutrients = nutrientPresenter.getShownNutrients();
		fillWithData(nutrients, allotments);
	}

	@Override
	protected void create() {
		form.setEntity(getPresenter().createNewEntity());
		form.openInModalPopup();
	}

	@Override
	protected IPresenter<Allotment> getPresenter() {
		return presenter;
	}

	@Override
	protected AbstractForm<Allotment> getForm() {
		return form;
	}

	private void testListener(@Observes TabSheetChangedEvent event) {
		if ("allotment".equals(event.getTab().getDescription())) {
			createContainer();
			update();
			grid.setContainerDataSource(c);
			grid.markAsDirty();
		}
	}

}
