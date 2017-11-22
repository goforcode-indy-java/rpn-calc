package com.lmig.gfc.rpn.models;

import java.util.Stack;

public abstract class TwoNumberCalculation implements Godoer, Undoer {

	private Stack<Double> stack;
	private Undoer undoer;

	public TwoNumberCalculation(Stack<Double> stack) {
		this.stack = stack;
	}
	
	public void goDoIt() {
		double first = stack.pop();
		double second = stack.pop();
		double result = doMath(first, second);
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
	}
	
	protected abstract double doMath(double first, double second);

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
	}

}







