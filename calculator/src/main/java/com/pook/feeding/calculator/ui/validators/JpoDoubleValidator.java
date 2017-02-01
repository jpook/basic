package com.pook.feeding.calculator.ui.validators;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.pook.feeding.calculator.ui.validators.interf.JpoValidator;
import com.vaadin.data.validator.RegexpValidator;

@SuppressWarnings("serial")
public class JpoDoubleValidator implements JpoValidator {

	private Double minimal = Double.MIN_VALUE;
	private Double maximal = Double.MAX_VALUE;
	private String regExp;

	public Double getMinimal() {
		return minimal;
	}

	public void setMinimal(Double minimal) {
		this.minimal = minimal;
	}

	public Double getMaximal() {
		return maximal;
	}

	public void setMaximal(Double maximal) {
		this.maximal = maximal;
	}

	public String getRegExp() {
		return regExp;
	}

	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
		System.out.print("validate class : " + (value == null ? "NULL" : value.getClass().getSimpleName()));

		if (value != null && value instanceof Double) {
			System.out.println("is double");
			Double d = (Double) value;
			if (regExp != null) {
				RegexpValidator rv = new RegexpValidator(regExp, "format does not match !");
				rv.validate(value);
			}
			if (!(d >= minimal && d <= maximal)) {
				System.out.println("throw !");
				throw new InvalidValueException("Not in Range : " + minimal + " - " + maximal);
			}
		} else
			System.out.println("correct !");
	}

}
