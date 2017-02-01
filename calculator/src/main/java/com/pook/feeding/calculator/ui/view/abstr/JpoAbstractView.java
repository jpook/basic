package com.pook.feeding.calculator.ui.view.abstr;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;
import com.pook.feeding.presenter.interf.IPresenter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public abstract class JpoAbstractView<T extends IEntity> extends MVerticalLayout {

	protected MTable<T> table;

	protected abstract IPresenter<T> getPresenter();

	protected abstract AbstractForm<T> getForm();
	
	protected Button addButton;

	protected void initTable() {
		addButton = new Button("Add");
		addButton.addClickListener(e->create());
		add(addButton);
		table.setBeans(getPresenter().loadData());
		table.addMValueChangeListener(e -> selectValue(e));
		expand(table);
		add(table);
		getForm().setSavedHandler(e -> save(e));
		getForm().setResetHandler(e->reset(e));
		getForm().setDeleteHandler(e -> delete(e));
	}

	protected void selectValue(MValueChangeEvent<T> e) {
		if (e.getValue() != null) {
			getForm().setEntity(e.getValue());
			getForm().openInModalPopup();
		}
	}

	protected void save(T entity) {
		getPresenter().save(entity);
		getForm().getPopup().close();
		listEntities();
		Notification.show("Saved");
	}

	protected void delete(T entity) {
		getPresenter().delete(entity);
		getForm().getPopup().close();
		listEntities();
		Notification.show("Deleted");
	}
	
	protected void reset(T entity) {
		getForm().setEntity(null);
		getForm().getPopup().close();
		listEntities();
	}

	protected void listEntities() {
		table.setBeans(getPresenter().loadData());
	}

	protected void create() {
		getForm().setEntity(getPresenter().createNewEntity());
		getForm().openInModalPopup();
	}

}
