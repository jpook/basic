package com.pook.feeding.calculator.model.db.entities.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class Nutrient extends AEntity {

	String name;
	Integer orderNo;
	String validator;
	Double minimal;
	Double maximal;
	String formreg;

	@OneToMany(mappedBy = "nutrient")
	List<NutrientValue> nutrientValues;

	@ManyToOne
	NutrientUnit nutrientUnit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrder() {
		return orderNo;
	}

	public void setOrder(Integer order) {
		this.orderNo = order;
	}

	public List<NutrientValue> getNutrientValues() {
		return nutrientValues;
	}

	public void setNutrientValues(List<NutrientValue> nutrientValues) {
		this.nutrientValues = nutrientValues;
	}

	public NutrientUnit getNutrientUnit() {
		return nutrientUnit;
	}

	public void setNutrientUnit(NutrientUnit nutrientUnit) {
		this.nutrientUnit = nutrientUnit;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	public Double getMinimal() {
		return minimal;
	}

	public void setMinimal(Double minimal) {
		this.minimal = minimal;
	}

	public Double getMaximal() {
		return maximal;
	}

	public void setMaximal(Double maximal) {
		this.maximal = maximal;
	}

	public String getFormreg() {
		return formreg;
	}

	public void setFormreg(String formreg) {
		this.formreg = formreg;
	}

	@Transient
	public String getNutrientUnitName() {
		if (getNutrientUnit() != null)
			return getNutrientUnit().getUnitName();
		else
			return null;
	}

}
