package com.pook.feeding.calculator.model.dao.abstr;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AModel<T> {

	public abstract EntityManager getEntityManager();

	public abstract Class<T> getEntityClass();

	public boolean saveEntity(Object entity) {
		boolean result = true;
		try {
			getEntityManager().merge(entity);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public List<T> getResultList() {
		List<T> result = null;
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(getEntityClass());
		Root<T> r = cq.from(getEntityClass());
		cq.select(r);
		TypedQuery<T> tq = getEntityManager().createQuery(cq);
		result = tq.getResultList();
		return result;
	}

	public void deleteEntity(Object entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T getEntityById(Class<T> clazz, Integer id) {
		T result = null;
		result = getEntityManager().find(clazz, id);
		return result;
	}
	
	public T create() {
		T result = null;
		try {
			result = getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}
