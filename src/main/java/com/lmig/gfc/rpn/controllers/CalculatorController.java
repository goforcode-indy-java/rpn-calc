package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsoluterOfOneNumber;
import com.lmig.gfc.rpn.models.AddTwoNumbersTogether;
import com.lmig.gfc.rpn.models.DivideTwoNumbersTogether;
import com.lmig.gfc.rpn.models.ExponentiateTwoNumbersTogether;
import com.lmig.gfc.rpn.models.Godoer;
import com.lmig.gfc.rpn.models.ItDoesThePushing;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbersTogether;
import com.lmig.gfc.rpn.models.SubtractTwoNumbersTogether;

@Controller
public class CalculatorController {
	
	private Stack<Double> stack;
	private Stack<Godoer> undoers;
	private Stack<Godoer> redoers;
	
	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<Godoer>();
		redoers = new Stack<Godoer>();
	}

	@GetMapping("/")
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack);
		mv.addObject("hasOneOrMoreNumbers", stack.size() >= 1);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		mv.addObject("hasRedoer", redoers.size() > 0);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		ItDoesThePushing pusher = new ItDoesThePushing(stack, value);
		return doOperation(pusher);
	}
	
	@PostMapping("/abs")
	public ModelAndView absoluteValue() {
		AbsoluterOfOneNumber absoluter = new AbsoluterOfOneNumber(stack);
		return doOperation(absoluter);
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		AddTwoNumbersTogether adder = new AddTwoNumbersTogether(stack);
		return doOperation(adder);
	}
	
	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {
		DivideTwoNumbersTogether divider = new DivideTwoNumbersTogether(stack);
		return doOperation(divider);
	}
	
	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {
		MultiplyTwoNumbersTogether multiplier = new MultiplyTwoNumbersTogether(stack);
		return doOperation(multiplier);
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		SubtractTwoNumbersTogether minuser = new SubtractTwoNumbersTogether(stack);
		return doOperation(minuser);
	}
	
	@PostMapping("/exp")
	public ModelAndView exponentitateTwoNumbers() {
		ExponentiateTwoNumbersTogether powerer = new ExponentiateTwoNumbersTogether(stack);
		return doOperation(powerer);
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		Godoer undoer = undoers.pop();
		undoer.undo(stack);
		redoers.push(undoer);
		
		return redirectToHome();
	}
	
	@PostMapping("/redo")
	public ModelAndView redo() {
		Godoer godoer = redoers.pop();
		godoer.goDoIt();
		undoers.push(godoer);
		
		return redirectToHome();
	}
	
	private ModelAndView doOperation(Godoer calcy) {
		calcy.goDoIt();
		undoers.push(calcy);
		redoers.clear();
		
		return redirectToHome();
	}
	
	private ModelAndView redirectToHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
}













