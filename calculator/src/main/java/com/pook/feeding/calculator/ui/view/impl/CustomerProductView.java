package com.pook.feeding.calculator.ui.view.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.vaadin.viritin.form.AbstractForm;

import com.pook.feeding.calculator.events.TabSheetChangedEvent;
import com.pook.feeding.calculator.model.db.entities.impl.CustomerProduct;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.TargetNutrientValue;
import com.pook.feeding.calculator.ui.view.abstr.JpoAbstractView;
import com.pook.feeding.presenter.impl.CustomerProductPresenter;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.pook.feeding.presenter.interf.IPresenter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

@SuppressWarnings("serial")
@SessionScoped
public class CustomerProductView extends JpoAbstractView<CustomerProduct> {
	// TODO: add validation
	// TODO: set column headers

	Grid grid = new Grid();
	GeneratedPropertyContainer gpc;

	@Inject
	CustomerProductPresenter presenter;

	@Inject
	NutrientPresenter nutrientPresenter;

	@Inject
	CustomerProductForm form;

	IndexedContainer c = new IndexedContainer();

	@PostConstruct
	public void init() {
		addButton = new Button("Add");
		addButton.addClickListener(e -> create());
		add(addButton);
		createContainer();
		update();

		grid.setContainerDataSource(gpc);
		grid.getColumn("calculate")
				 .setRenderer(new ButtonRenderer(e->presenter.calculate(e.getItemId())))
				.setHeaderCaption("Calc");
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
		CustomerProduct a = (CustomerProduct) ((SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();

		if (a != null) {
			System.out.println("selected : " + a.getName());
			form.setEntity(a);
			form.openInModalPopup();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected GeneratedPropertyContainer createContainer() {
		ArrayList al = new ArrayList(c.getContainerPropertyIds());
		if (al != null) {
			for (int i = 0; i < al.size(); i++) {
				c.removeContainerProperty(al.get(i));
			}
		}

		c.addContainerProperty("name", String.class, "");
		c.addContainerProperty("targetDate", Date.class, null);
		c.addContainerProperty("totalWeight", Double.class, 0.0);
		List<Nutrient> nutrients = nutrientPresenter.getShownNutrients();
		if (nutrients != null) {
			for (int k = 0; k < nutrients.size(); k++) {
				Nutrient nutrient = nutrients.get(k);
				c.addContainerProperty("nutrients." + nutrient.getName() + "_" + nutrient.getId(), Double.class, 0.0);
			}
		}

		gpc = new GeneratedPropertyContainer(c);
		gpc.addGeneratedProperty("calculate", new PropertyValueGenerator() {

			@Override
			public Object getValue(Item item, Object itemId, Object propertyId) {
				return "Calculate";
			}

			@Override
			public Class<String> getType() {
				return String.class;
			}
		});

		return gpc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fillWithData(List<Nutrient> nutrients, List<CustomerProduct> customerProducts) {
		if (customerProducts != null) {
			for (int i = 0; i < customerProducts.size(); i++) {
				CustomerProduct a = customerProducts.get(i);
				c.addItem(a);
				Item newItem = c.getItem(a);
				newItem.getItemProperty("name").setValue(a.getName());
				newItem.getItemProperty("targetDate").setValue(a.getTargetDate());
				newItem.getItemProperty("totalWeight").setValue(a.getTotalWeight());
				for (int r = 0; r < nutrients.size(); r++) {
					Nutrient nutrient = nutrients.get(r);
					TargetNutrientValue nv = presenter.getValueByNutrient(a, nutrient);

					if (nv != null) {
						Property p = newItem
								.getItemProperty("nutrients." + nutrient.getName() + "_" + nutrient.getId());
						p.setValue(nv.getValue());
					}
				}
			}
		}
	}

	protected void save(CustomerProduct entity) {
		getPresenter().save(entity);
		form.getPopup().close();
		update();
		Notification.show("Saved");
	}

	protected void delete(CustomerProduct entity) {
		getPresenter().delete(entity);
		form.getPopup().close();
		update();
		Notification.show("Deleted");
	}

	protected void reset(CustomerProduct entity) {
		getForm().setEntity(null);
		getForm().getPopup().close();
	}

	private void update() {
		grid.setStyleName("jpogrid");
		grid.setCellStyleGenerator(cell -> "calculate".equals(cell.getPropertyId()) ? "imagecol" : null);
		System.out.println("styled");
		c.removeAllItems();
		List<CustomerProduct> customerProducts = (List<CustomerProduct>) presenter.loadData();
		List<Nutrient> nutrients = nutrientPresenter.getShownNutrients();
		fillWithData(nutrients, customerProducts);

	}

	@Override
	protected void create() {
		form.setEntity(getPresenter().createNewEntity());
		form.openInModalPopup();
	}

	@Override
	protected IPresenter<CustomerProduct> getPresenter() {
		return presenter;
	}

	@Override
	protected AbstractForm<CustomerProduct> getForm() {
		return form;
	}

	private void testListener(@Observes TabSheetChangedEvent event) {
		if ("customerProduct".equals(event.getTab().getDescription())) {
			createContainer();
			update();

			grid.setContainerDataSource(gpc);
			grid.markAsDirty();
		}
	}

}
