package com.pook.feeding.calculator.model.db.entities.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class NutrientUnit extends AEntity {

	String unitName;

	@OneToMany(mappedBy = "nutrientUnit")
	List<Nutrient> nutrients;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<Nutrient> getNutrients() {
		return nutrients;
	}

	public void setNutrients(List<Nutrient> nutrients) {
		this.nutrients = nutrients;
	}

	public String toString() {
		return unitName;
	}

}
