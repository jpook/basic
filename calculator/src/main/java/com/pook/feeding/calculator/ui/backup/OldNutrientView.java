package com.pook.feeding.calculator.ui.backup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.ui.view.abstr.ListViewAbstr;
import com.pook.feeding.presenter.impl.NutrientPresenter;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
@SessionScoped
@Named("OldNutrientView")
public class OldNutrientView extends ListViewAbstr<Nutrient> {

	@Inject
	NutrientPresenter presenter;

	@PostConstruct
	public void init() {
//		presenter.setView(this);
	}

	@Override
	protected void setBeanItemContainer() {
		beanContainer =  new BeanItemContainer<Nutrient>(Nutrient.class);
		beanContainer.addNestedContainerProperty("nutrientUnit.unitName");
	}

	@Override
	protected void createTable() {
		table.setVisibleColumns((Object[]) new String[] { "id", "name","nutrientUnit.unitName"  });
		table.setColumnHeaders(new String[] { "ID", "Name","Unit" });
	}

	public NutrientPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(NutrientPresenter presenter) {
		this.presenter = presenter;
	}

}
