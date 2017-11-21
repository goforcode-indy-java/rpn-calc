package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class ExponentiateTwoNumbersTogether extends TwoNumberCalculation {

	public ExponentiateTwoNumbersTogether(Stack<Double> stack) {
		super(stack);
	}

	@Override
	protected double doMath(double first, double second) {
		return Math.pow(second, first);
	}

}
