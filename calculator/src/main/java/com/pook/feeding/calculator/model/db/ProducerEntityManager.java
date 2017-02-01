package com.pook.feeding.calculator.model.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProducerEntityManager {

	@PersistenceContext
	private EntityManager em;
	
	@Produces
	@ApplicationScoped
	public EntityManager createEntityManager() {
		return em;
	}
	
	public void dispose(@Disposes EntityManager em){
		em.close();
	}
	
}
