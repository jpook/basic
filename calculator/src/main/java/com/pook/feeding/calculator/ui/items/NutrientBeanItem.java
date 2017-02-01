package com.pook.feeding.calculator.ui.items;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;

@SuppressWarnings("serial")
@SessionScoped
@Named("NutrientBeanItem")
public class NutrientBeanItem extends EntityBeanItem<Nutrient> implements Serializable {

	public NutrientBeanItem() {
		super(Nutrient.class);
	}

}
