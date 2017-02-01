package com.pook.feeding.calculator.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pook.feeding.calculator.model.dao.abstr.AModel;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;

@Stateless
public class NutrientModel extends AModel<Nutrient> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Nutrient> getEntityClass() {
		return Nutrient.class;
	}

}
