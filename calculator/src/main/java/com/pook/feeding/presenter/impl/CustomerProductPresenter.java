package com.pook.feeding.presenter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer;

import com.pook.feeding.calculator.model.dao.impl.CustomerProductModel;
import com.pook.feeding.calculator.model.db.entities.impl.Allotment;
import com.pook.feeding.calculator.model.db.entities.impl.CustomerProduct;
import com.pook.feeding.calculator.model.db.entities.impl.Nutrient;
import com.pook.feeding.calculator.model.db.entities.impl.NutrientValue;
import com.pook.feeding.calculator.model.db.entities.impl.TargetNutrientValue;
import com.pook.feeding.calculator.ui.view.impl.CustomerProductView;

@SuppressWarnings("serial")
@SessionScoped
public class CustomerProductPresenter extends APresenter<CustomerProduct> {

	CustomerProductView view;

	@Inject
	CustomerProductModel model;

	public void init() {
	}

	public CustomerProductView getView() {
		return view;
	}

	public void setView(CustomerProductView view) {
		this.view = view;
	}

	@Override
	protected CustomerProductModel getModel() {
		return model;
	}

	@Override
	protected Class<CustomerProduct> getClazz() {
		return CustomerProduct.class;
	}

	public List<Nutrient> getNutrients() {
		return null;
	}

	public TargetNutrientValue getValueByNutrient(CustomerProduct a, Nutrient n) {
		return model.getValueByNutrient(a, n);
	}

	public CustomerProduct createNewEntity() {
		CustomerProduct a = getModel().create();
		List<Nutrient> nutrients = model.getMandatoryNutrients();
		if (nutrients != null) {
			for (int i = 0; i < nutrients.size(); i++) {
				getValueByNutrient(a, nutrients.get(i));
			}
		}
		return a;
	}

	public void calculate(Object itemId) {
		System.out.println("calculating : " + itemId.getClass().getSimpleName());
		CustomerProduct customerProduct = (CustomerProduct) itemId;
		List<Allotment> availableAllotments = model.getAvailableAllotments();
		if (availableAllotments == null || availableAllotments.size() == 0 || customerProduct == null)
			return;
		double[][] coefficientMatrix = null;
		for (int i = 0; i < availableAllotments.size(); i++) {
			Allotment a = availableAllotments.get(i);
			List<NutrientValue> listNutrientValues = model.getSortedValues(a);
			if (listNutrientValues == null || listNutrientValues.size() == 0)
				continue;
			for (int j = 0; j < listNutrientValues.size(); j++) {
				if (coefficientMatrix == null)
					coefficientMatrix = new double[availableAllotments.size()][listNutrientValues.size()];
				NutrientValue nv = listNutrientValues.get(j);
				coefficientMatrix[i][j] = nv.getValue();
			}
		}

		RealMatrix rm = new Array2DRowRealMatrix(coefficientMatrix);
		System.out.println(rm.toString());

		System.out.println("____________________________________________________________");

		

	}

}
