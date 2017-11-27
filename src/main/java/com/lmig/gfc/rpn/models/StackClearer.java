package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class StackClearer implements Godoer {
	
	private Stack<Double> sourceStack;
	private Stack<Double> stackOfHolding;

	public StackClearer(Stack<Double> stack) {
		this.sourceStack = stack;
		this.stackOfHolding = new Stack<Double>();
	}

	@Override
	public void undo(Stack<Double> stack) {
		moveFromOneStackToAnother(stackOfHolding, sourceStack);
	}

	@Override
	public void goDoIt() {
		moveFromOneStackToAnother(sourceStack, stackOfHolding);
	}
	
	private void moveFromOneStackToAnother(Stack<Double> from, Stack<Double> to) {
		while (!from.empty()) {
			Double value = from.pop();
			to.push(value);
		}
	}

}
















