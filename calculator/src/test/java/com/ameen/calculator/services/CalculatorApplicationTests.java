package com.ameen.calculator.services;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ameen.calculator.exceptions.EmptyInputException;
import com.ameen.calculator.exceptions.InvalidCharacterException;

@SpringBootTest
class CalculatorApplicationTests {
	
	@Autowired
	CalculatorService service;
	
	//Positive scenarios
	//Inputs given by Seezle
	@Test
	public void calculateTestSuccess1() {	    
	    Double result = service.calculate("2+3+3");
	    Assertions.assertEquals(8, result);
	}
	
	@Test
	public void calculateTestSuccess2() {
	    Double result = service.calculate("2+3-3");
	    Assertions.assertEquals(2, result);
	}
	
	@Test
	public void calculateTestSuccess3() {
	    Double result = service.calculate("5/2-3");
	    Assertions.assertEquals(-0.5, result);
	}
	
	//User given inputs
	@Test
	public void calculateTestSuccess4() {
	    Double result = service.calculate("4*5+6*7-33/9+18/3*44+6-33+8-59/7+14*7-45/7-2/5+13/1+16*6+7");
	    Assertions.assertEquals(502.07619047619045, result);
	}
	
	//Input expression with space in between
	@Test
	public void calculateTestWithSpace() {
		Double result = service.calculate("5/2- 3");
	    Assertions.assertEquals(-0.5, result);
	}
	
	//Empty input
	@Test
	public void calculateTestWithEmptyInput() {
	    Exception exception = assertThrows(EmptyInputException.class, () -> {
	    	service.calculate("");
	    });
	    String expectedMessage = "Empty input, please check the input.";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	//Invalid character exception
	@Test
	public void calculateTestWithInvalidCharacter() {
	    Exception exception = assertThrows(InvalidCharacterException.class, () -> {
	    	service.calculate("5/2- 3^3");
	    });
	    String expectedMessage = "Invalid characters present, please check the input.";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	//Alphabet character exception
	@Test
	public void calculateTestWithAlphabet() {
	    Exception exception = assertThrows(InvalidCharacterException.class, () -> {
	    	service.calculate("5/b-3");
	    });
	    String expectedMessage = "Invalid characters present, please check the input.";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	//Divide by zero exception
	@Test
	public void whenDerivedExceptionThrown_thenAssertionSucceds() {
	    Exception exception = assertThrows(ArithmeticException.class, () -> {
	    	service.calculate("5/0");
	    });
	    String expectedMessage = "/ by zero";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
