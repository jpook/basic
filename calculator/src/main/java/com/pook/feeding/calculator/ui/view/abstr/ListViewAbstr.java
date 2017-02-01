package com.pook.feeding.calculator.ui.view.abstr;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;
import com.pook.feeding.calculator.ui.items.EntityBeanItem;
import com.pook.feeding.presenter.interf.IPresenter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/*
 * TODO: remove @Inject from abstract class
 * replace by abstract getter, to be implemented by extending class
 */
@SuppressWarnings("serial")
public abstract class ListViewAbstr<T extends IEntity> extends CustomComponent {

	protected abstract IPresenter<T> getPresenter();
	
	protected abstract void setBeanItemContainer();

	protected abstract void createTable();

	protected BeanItemContainer<T> beanContainer;
	protected VerticalLayout layout;
	protected Table table;
	protected Button addButton;

	@Inject
	protected EntityBeanItem<T> entityBeanItem;

	@Inject
	protected DetailViewAbstr<T> detailView;
	
	@PostConstruct
	public void createLayout() {
		setBeanItemContainer();
		detailView.setListView(this);
		layout = new VerticalLayout();
		layout.addComponent(getTableLayout());
		layout.addComponent(detailView);
		setCompositionRoot(layout);
	}

	private Layout getTableLayout() {
		VerticalLayout tableLayout = new VerticalLayout();
		
		createGenericTable();
		refresh();
		
		createTable();

		tableLayout.addComponent(table);
		table.addValueChangeListener(e -> {
			if (table.getValue() != null)
				valueChange(table.getValue());
		});

		addButton = new Button("Add");
		addButton.addClickListener(e -> addItem());
		tableLayout.addComponent(addButton);
		tableLayout.setMargin(true);
		tableLayout.setSpacing(true);
		tableLayout.setSizeFull();
		return tableLayout;
	}

	private void createGenericTable() {
		table = new Table();
		table.setContainerDataSource(beanContainer);
		table.setSelectable(true);
		table.setImmediate(true);
		table.setPageLength(Math.min(10,table.size()));
	}

	private void addItem() {
		T newEntity = getPresenter().createNewEntity();
		BeanItem<T> nbi = new BeanItem<T>(newEntity);
		entityBeanItem.setBeanItem(nbi);
		detailView.setEntity();
	}

	public void refresh() {
		Collection<T> list = getPresenter().loadData();
		if (list != null && list.size() > 0) {
			beanContainer.removeAllItems();
			beanContainer.addAll(list);
			table.refreshRowCache();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void valueChange(Object object) {
		entityBeanItem.setBeanItem((BeanItem<T>) table.getContainerDataSource().getItem(object));
		detailView.setEntity();
		table.setEnabled(false);
	}

	public void cancelEdit() {
		table.setEnabled(true);
		table.setValue(null);
		table.setPageLength(table.size());
	}

}
