package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Swapper implements Godoer {
	
	private Stack<Double> stack;

	public Swapper(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> undoStack) {
		swap(undoStack);
	}

	@Override
	public void goDoIt() {
		swap(stack);
	}
	
	private void swap(Stack<Double> stack) {
		Double mostRecent = stack.pop();
		Double secondMostRecent = stack.pop();
		stack.push(mostRecent);
		stack.push(secondMostRecent);
	}

}











