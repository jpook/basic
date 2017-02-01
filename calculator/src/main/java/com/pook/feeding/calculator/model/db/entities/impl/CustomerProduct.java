package com.pook.feeding.calculator.model.db.entities.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class CustomerProduct extends AEntity {

	@Column(unique = true, nullable = false, length = 80)
	String name;

	Double totalWeight;
	Date targetDate;

	@ManyToOne
	Customer customer;

	@ManyToOne
	Status status;

	@OneToMany(mappedBy = "valueOwner", cascade = CascadeType.ALL)
	List<TargetNutrientValue> targetNutrientValues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<TargetNutrientValue> getTargetNutrientValues() {
		return targetNutrientValues;
	}

	public void setTargetNutrientValues(List<TargetNutrientValue> targetNutrientValues) {
		this.targetNutrientValues = targetNutrientValues;
	}

}
