package com.pook.feeding.calculator.model.db.entities.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class NutrientValue extends AEntity {

	Double value;
	
	@ManyToOne
	private Nutrient nutrient;
	
	@ManyToOne
	private Allotment valueOwner;

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

	public Allotment getValueOwner() {
		return valueOwner;
	}

	public void setValueOwner(Allotment valueOwner) {
		this.valueOwner = valueOwner;
	}

}
