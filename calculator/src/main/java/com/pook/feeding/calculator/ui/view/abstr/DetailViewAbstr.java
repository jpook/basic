package com.pook.feeding.calculator.ui.view.abstr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.pook.feeding.calculator.model.db.entities.interf.IEntity;
import com.pook.feeding.calculator.ui.items.EntityBeanItem;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
public abstract class DetailViewAbstr<T extends IEntity> extends CustomComponent {

	@Inject
	protected EntityBeanItem<T> beanItem;

	protected abstract BeanFieldGroup<T> createBeanFieldGroup();

	protected abstract void doBindings(Layout layout);
	
	protected abstract void reloadData();
	
	protected ListViewAbstr<T> listView;
	protected BeanFieldGroup<T> beanFieldGroup;
	protected Layout layout = null;

	private Button saveButton;
	private Button cancelButton;
	private Button deleteButton;

	@PostConstruct
	public void init() {
		layout = getFormLayout();
		layout.setVisible(false);
		setCompositionRoot(layout);
	}

	private Layout getFormLayout() {
		final FormLayout formLayout = new FormLayout();
		formLayout.setCaption("Details");
		beanFieldGroup = createBeanFieldGroup();
		beanFieldGroup.setBuffered(true);
		doBindings(formLayout);
		formLayout.addComponent(getButtonLayout());
		formLayout.setMargin(true);
		formLayout.setSpacing(true);
		formLayout.setSizeFull();

		
		beanFieldGroup.bindMemberFields(this);
		beanFieldGroup.setItemDataSource(beanItem.getBeanItem());
		return formLayout;
	}

	public void setEntity() {
		System.out.println("setEntity()");
		layout.setVisible(true);
		beanFieldGroup.setItemDataSource(beanItem.getBeanItem());
		reloadData();
	}

	private Layout getButtonLayout() {
		saveButton = new Button("Save");
		saveButton.addClickListener(e -> save());

		cancelButton = new Button("Cancel");
		cancelButton.addClickListener(e -> cancel());

		deleteButton = new Button("Delete");
		deleteButton.addClickListener(e -> delete());

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addComponents(saveButton, cancelButton, deleteButton);
		return buttonLayout;
	}

	private void cancel() {
		layout.setVisible(false);
		beanFieldGroup.discard();
		listView.cancelEdit();
	}

	private void save() {
		try {
			beanFieldGroup.commit();
			listView.getPresenter().save(beanFieldGroup.getItemDataSource().getBean());
			layout.setVisible(false);
			listView.refresh();
			listView.cancelEdit();
		} catch (CommitException e) {
			e.printStackTrace();
		}

	}

	private void delete() {
		listView.getPresenter().delete(beanFieldGroup.getItemDataSource().getBean());
		layout.setVisible(false);
		beanFieldGroup.setItemDataSource((BeanItem<T>) null);
		listView.refresh();
	}

	public void setListView(ListViewAbstr<T> listView) {
		this.listView = listView;
	}

}
