package com.pook.feeding.calculator.model.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pook.feeding.calculator.model.dao.abstr.AModel;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientUnit;

@Stateless
public class NutrientUnitModel extends AModel<NutrientUnit> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<NutrientUnit> getEntityClass() {
		return NutrientUnit.class;
	}


}
