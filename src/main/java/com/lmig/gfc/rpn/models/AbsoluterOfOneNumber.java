package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AbsoluterOfOneNumber implements Godoer, Undoer {

	private Stack<Double> stack;
	private OneArgumentUndoer myUndoer;
	
	public AbsoluterOfOneNumber(Stack<Double> stack) {
		this.stack = stack;
	}
	
	public void goDoIt() {
		double value = stack.pop();
		double result = Math.abs(value);
		stack.push(result);
		myUndoer = new OneArgumentUndoer(value);
	}

	@Override
	public void undo(Stack<Double> stack) {
		myUndoer.undo(stack);
	}
	
}













