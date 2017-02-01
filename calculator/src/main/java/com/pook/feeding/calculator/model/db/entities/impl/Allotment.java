package com.pook.feeding.calculator.model.db.entities.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class Allotment extends AEntity {

	@Column(unique = true, nullable = false, length = 80)
	String name;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateOfHarvest;
	Double harvestWeight;
	Double currentWeight;
	
	@OneToMany(mappedBy="valueOwner", cascade = CascadeType.ALL)
	List<NutrientValue> nutrientValues;
	
	@ManyToOne
	Owner owner;
	
	@ManyToOne
	Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfHarvest() {
		return dateOfHarvest;
	}

	public void setDateOfHarvest(Date dateOfHarvest) {
		this.dateOfHarvest = dateOfHarvest;
	}

	public Double getHarvestWeight() {
		return harvestWeight;
	}

	public void setHarvestWeight(Double harvestWeight) {
		this.harvestWeight = harvestWeight;
	}

	public List<NutrientValue> getNutrientValues() {
		return nutrientValues;
	}

	public void setNutrientValues(List<NutrientValue> nutrientValues) {
		this.nutrientValues = nutrientValues;
	}

	public Double getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(Double currentWeight) {
		this.currentWeight = currentWeight;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
