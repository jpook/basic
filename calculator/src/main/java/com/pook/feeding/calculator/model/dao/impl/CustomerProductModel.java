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
import com.pook.feeding.calculator.model.db.entities.impl.CustomerProduct;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;
import com.pook.feeding.calculator.model.db.entities.impl.TargetNutrientValue;

@Stateless
public class CustomerProductModel extends AModel<CustomerProduct> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<CustomerProduct> getEntityClass() {
		return CustomerProduct.class;
	}

	public TargetNutrientValue getValueByNutrient(CustomerProduct customerProduct, Nutrient nutrient) {
		TargetNutrientValue result = null;
		Query q = em.createQuery(
				"SELECT n FROM TargetNutrientValue n WHERE n.valueOwner = :valueOwner AND n.nutrient = :nutrient");
		q.setParameter("valueOwner", customerProduct);
		q.setParameter("nutrient", nutrient);
		try {
			result = (TargetNutrientValue) q.getSingleResult();
		} catch (NoResultException e) {
			Class<TargetNutrientValue> nutrientValueClass = TargetNutrientValue.class;
			try {
				result = nutrientValueClass.newInstance();
				result.setValueOwner(customerProduct);
				customerProduct.getTargetNutrientValues().add(result);
				result.setNutrient(nutrient);
			} catch (InstantiationException | IllegalAccessException e1) {
				result = null;
				System.out.println("result is null");
			}
		}
		return result;
	}

	@Override
	public CustomerProduct create() {
		CustomerProduct result = super.create();
		result.setTotalWeight(0.0);
		result.setTargetDate(new Date());
		result.setCreated(new Date());
		result.setModified(new Date());
		result.setTargetNutrientValues(new ArrayList<TargetNutrientValue>());
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Nutrient> getMandatoryNutrients() {
		Query q = em.createQuery("SELECT n FROM Nutrient n");
		List<Nutrient> nutrients = q.getResultList();
		return nutrients;
	}

	@SuppressWarnings("unchecked")
	public List<Allotment> getAvailableAllotments() {
		return em.createQuery("SELECT a FROM Allotment a WHERE a.currentWeight > 0").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<NutrientValue> getSortedValues(Allotment a) {
		Query q = em.createQuery("SELECT n FROM NutrientValue n WHERE n.valueOwner = :a ORDER BY n.nutrient.orderNo ASC");
		q.setParameter("a", a);
		return q.getResultList();
	}

}
