package com.pook.feeding.calculator.model.db.entities.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class TargetNutrientValue extends AEntity {

	Double value;
	
	@ManyToOne
	private Nutrient nutrient;
	
	@ManyToOne
	private CustomerProduct valueOwner;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Nutrient getNutrient() {
		return nutrient;
	}

	public void setNutrient(Nutrient nutrient) {
		this.nutrient = nutrient;
	}

	public CustomerProduct getValueOwner() {
		return valueOwner;
	}

	public void setValueOwner(CustomerProduct valueOwner) {
		this.valueOwner = valueOwner;
	}

}
