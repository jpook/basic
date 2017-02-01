package com.pook.feeding.calculator.ui.view.impl;

import javax.enterprise.context.ApplicationScoped;

import com.pook.feeding.calculator.ui.validators.interf.JpoValidator;
import com.vaadin.data.Validator;

@ApplicationScoped
public class ValidatorFacade {

	private final static String pack = "com.pook.feeding.calculator.ui.validators";

	public Validator findValidator(String validatorClassName, Double min, Double max, String regexp) {
		JpoValidator result = null;
		try {
			Class<?> c = Class.forName(pack + "." + validatorClassName);
			Object o = c.newInstance();
			if (o instanceof JpoValidator) {
				result = (JpoValidator) o;
				result.setMinimal(min);
				result.setMaximal(max);
				result.setRegExp(regexp);
			}
			System.out.println("found it !");
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}
