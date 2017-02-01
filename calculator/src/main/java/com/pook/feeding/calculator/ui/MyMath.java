package com.pook.feeding.calculator.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer;

public class MyMath {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]
		// { 3, 5 }, 0);
		//
		// Collection constraints = new ArrayList();
		// constraints.add(new LinearConstraint(new double[] { 2, 8 },
		// Relationship.LEQ, 13));
		// constraints.add(new LinearConstraint(new double[] { 5, -1 },
		// Relationship.LEQ, 11));
		//
		// constraints.add(new LinearConstraint(new double[] { 1, 0 },
		// Relationship.GEQ, 0));
		// constraints.add(new LinearConstraint(new double[] { 0, 1 },
		// Relationship.GEQ, 0));
		// LinearConstraintSet lcs = new LinearConstraintSet(constraints);
		// PointValuePair solution = null;
		//
		// try {
		// solution = new SimplexSolver().optimize(f, lcs, GoalType.MAXIMIZE);
		// } catch (TooManyIterationsException e) {
		// e.printStackTrace();
		// }
		//
		// if (solution != null) {
		// double max = solution.getValue();
		// System.out.println("Opt: " + max);
		// }
		//
		// for (int i = 0; i < 2; i++) {
		// System.out.println(solution.getPoint()[i] + "\t");
		// }

		LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 23,359,235 }, 2452);
		ObjectiveFunction of = new ObjectiveFunction(f);

		Collection constraints = new ArrayList();
//		constraints.add(new LinearConstraint(new double[] { 12, 15, 8 }, Relationship.EQ, 12));
//		constraints.add(new LinearConstraint(new double[] { 650, 480, 700 }, Relationship.EQ, 650));
//		constraints.add(new LinearConstraint(new double[] { 77, 75, 79 }, Relationship.EQ, 77));
//
//		constraints.add(new LinearConstraint(new double[] { 1, 0, 0 }, Relationship.GEQ, 0));
//		constraints.add(new LinearConstraint(new double[] { 0, 1, 0 }, Relationship.GEQ, 0));
//		constraints.add(new LinearConstraint(new double[] { 0, 0, 1 }, Relationship.GEQ, 0));
//
//		constraints.add(new LinearConstraint(new double[] { 1, 0, 0 }, Relationship.LEQ, 500));
//		constraints.add(new LinearConstraint(new double[] { 0, 1, 0 }, Relationship.LEQ, 900));
//		constraints.add(new LinearConstraint(new double[] { 0, 0, 1 }, Relationship.LEQ, 130));

		LinearConstraintSet lcs = new LinearConstraintSet(constraints);
		PointValuePair solution = null;
		NelderMeadSimplex nms = new NelderMeadSimplex(3);
		try {
			solution = new SimplexOptimizer(0.3, 0.3).optimize(of, lcs, GoalType.MAXIMIZE, nms,
					new InitialGuess(new double[] { 1, 1, 10 }), new MaxEval(25000));
		} catch (TooManyIterationsException e) {
			e.printStackTrace();
		}

		if (solution == null) {
			// double max = solution.getValue();
			// System.out.println("Opt: " + max);
			return;
		}

		double[] val = solution.getPoint();
		for (int i = 0; i < val.length; i++) {
			System.out.println(val[i] + "\t");
		}
	}

}
