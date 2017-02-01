package com.pook.feeding.calculator.ui.validators.interf;

import com.vaadin.data.Validator;

public interface JpoValidator extends Validator {

	public void setMinimal(Double minimal);
	public void setMaximal(Double maximal);
	public void setRegExp(String regExp);
	
}
