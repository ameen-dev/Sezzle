package com.ameen.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameen.calculator.services.CalculatorService;

@RestController
public class Controller {
	
	@Autowired
	CalculatorService service;

	//Root mapping to check that the application is up
	@RequestMapping("/")
	public String home () {
		return "Welcome to calculator application";
	}
	
	//Post call mapping to calculator service
	@PostMapping("/calculate")
	public String calculate (@RequestBody String input) {
		try {
			return service.calculate(input)+"";
		}catch(ArithmeticException e) {
			return "ArithmeticException : "+ e.getMessage();
		}catch(NumberFormatException e) {
			return "NumberFormatException : "+ e.getMessage();
		}catch(Exception e) {
			return "Exception : "+ e.getMessage();
		}
	}
}
