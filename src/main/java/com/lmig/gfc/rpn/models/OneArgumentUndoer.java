package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer implements Undoer {

	private double first;
	
	public OneArgumentUndoer(double first) {
		this.first = first;
	}
	
	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
	}
	
	protected void parentUndo(Stack<Double> stack) {
		stack.push(first);
	}
	
}












