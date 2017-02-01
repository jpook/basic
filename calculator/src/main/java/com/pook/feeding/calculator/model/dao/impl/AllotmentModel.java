package com.pook.feeding.calculator.model.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pook.feeding.calculator.model.dao.abstr.AModel;
import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;

@Stateless
public class AllotmentModel extends AModel<Allotment> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Allotment> getEntityClass() {
		return Allotment.class;
	}

	public NutrientValue getValueByNutrient(Allotment allotment, Nutrient nutrient) {
		NutrientValue result = null;
		Query q = em.createQuery(
				"SELECT n FROM NutrientValue n WHERE n.valueOwner = :valueOwner AND n.nutrient = :nutrient");
		q.setParameter("valueOwner", allotment);
		q.setParameter("nutrient", nutrient);
		try {
			result = (NutrientValue) q.getSingleResult();
		} catch (NoResultException e) {
			Class<NutrientValue> nutrientValueClass = NutrientValue.class;
			try {
				result = nutrientValueClass.newInstance();
				result.setValueOwner(allotment);
				allotment.getNutrientValues().add(result);
				result.setNutrient(nutrient);
			} catch (InstantiationException | IllegalAccessException e1) {
				result = null;
				System.out.println("result is null");
			}
		}
		return result;
	}

	@Override
	public Allotment create() {
		Allotment result = super.create();
		result.setCurrentWeight(0.0);
		result.setHarvestWeight(0.0);
		result.setDateOfHarvest(new Date());
		result.setCreated(new Date());
		result.setModified(new Date());
		result.setNutrientValues(new ArrayList<NutrientValue>());
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Nutrient> getMandatoryNutrients() {
		Query q = em.createQuery("SELECT n FROM Nutrient n");
		List<Nutrient> nutrients = q.getResultList();
		return nutrients;
	}

}
