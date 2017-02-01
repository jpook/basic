package com.pook.feeding.calculator.ui.items;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;

@SuppressWarnings("serial")
@SessionScoped
@Named("NutrientUnitBeanItem")
public class NutrientUnitBeanItem extends EntityBeanItem<NutrientUnit> implements Serializable {

	public NutrientUnitBeanItem() {
		super(NutrientUnit.class);
	}

}
