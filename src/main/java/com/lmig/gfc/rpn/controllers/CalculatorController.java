package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class CalculatorController {
	
	private Stack<Double> stack;
	private Stack<Undoer> undoers;
	
	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
	}

	@GetMapping("/")
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		stack.push(value);
		undoers.push(new PushUndoer());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/abs")
	public ModelAndView absoluteValue() {
		double value = stack.pop();
		undoers.push(new OneArgumentUndoer(value));
		
//		double result = Math.abs(value);
		if (value < 0) {
			value = -1 * value;
		}
		stack.push(value);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = first + second;
		stack.push(result);
		undoers.push(new TwoArgumentUndoer(first, second));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = second - first;
		stack.push(result);
		undoers.push(new TwoArgumentUndoer(first, second));

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		Undoer undoer = undoers.pop();
		undoer.undo(stack);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
}













