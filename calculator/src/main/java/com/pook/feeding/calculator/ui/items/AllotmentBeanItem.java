package com.pook.feeding.calculator.ui.items;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.pook.feeding.calculator.model.db.entities.impl.Allotment;

@SuppressWarnings("serial")
@SessionScoped
@Named("AllotmentBeanItem")
public class AllotmentBeanItem extends EntityBeanItem<Allotment> implements Serializable {

	public AllotmentBeanItem() {
		super(Allotment.class);
	}

}
