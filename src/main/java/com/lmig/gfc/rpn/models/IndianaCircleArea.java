package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class IndianaCircleArea implements Godoer {
	
	private Stack<Double> stack;
	private double valueToUndo;
	
	public IndianaCircleArea(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		stack.push(valueToUndo);
	}

	@Override
	public void goDoIt() {
		Double value = stack.pop();
		valueToUndo = value;
		int realValue = value.intValue();
		int result = IndianaMath.areaOfCircle(realValue);
		stack.push((double) result);
	}

}









