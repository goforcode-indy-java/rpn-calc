package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class Rotator implements Godoer {
	
	private Stack<Double> stack;

	public Rotator(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> stack) {
		Double thirdMostRecent = stack.pop();
		Double mostRecent = stack.pop();
		Double secondMostRecent = stack.pop();
		stack.push(thirdMostRecent);
		stack.push(secondMostRecent);
		stack.push(mostRecent);		
	}

	@Override
	public void goDoIt() {
		Double mostRecent = stack.pop();
		Double secondMostRecent = stack.pop();
		Double thirdMostRecent = stack.pop();
		stack.push(secondMostRecent);
		stack.push(mostRecent);
		stack.push(thirdMostRecent);
	}

}















