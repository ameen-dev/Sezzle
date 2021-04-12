package com.ameen.calculator.services.impls;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.ameen.calculator.exceptions.EmptyInputException;
import com.ameen.calculator.exceptions.InvalidCharacterException;
import com.ameen.calculator.services.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService{
	
    public double calculate(String input){
    	try {
    		//Removing spaces from input
    		input = input.replaceAll("\\s", "");
    		
    		//Check if the input is not empty
    		if(input.equals("")) {
    			throw new EmptyInputException("Empty input, please check the input.");
    		}
    		
    		//Check if it allows only basic 4 operators
    		//^[\\d\\+*/-]+$ If we need not allow space too in the same condition
    		if(!input.matches("^[\\d\\.\\+*/-]*$")) {
    			throw new InvalidCharacterException("Invalid characters present, please check the input.");
    		}
    		
            Stack<Double> numberStack = new Stack<Double>();
            Stack<Operators> operatorStack = new Stack<Operators>();
            for(int i = 0; i < input.length(); i++){
                try{
                    double[] number = parseNumber(input, i);
                    numberStack.push(number[0]);

                    i=(int) number[1];
                    //i += Double.toString(number).length();
                    if(number[1] >= input.length()){
                        break;
                    }

                    Operators operator = parseOperator(input, i);
                    workOnTopMost(numberStack, operatorStack, operator);
                    operatorStack.push(operator);
                } catch(NumberFormatException nfe){
                    throw nfe;
                }
            }
            workOnTopMost(numberStack, operatorStack, Operators.BLANK);
            if(numberStack.size() == 1 && operatorStack.size() == 0){
                return numberStack.pop();
            }
            return 0;
    	}catch(ArithmeticException e) {
    		throw e;
    	}catch(Exception e) {
    		throw e;
    	}
    }

    private void workOnTopMost(Stack<Double> numberStack, Stack<Operators> operatorStack, Operators futureTop){
        while(numberStack.size() >= 2 && operatorStack.size() >= 1){
            if(operatorPriority(futureTop) <= operatorPriority(operatorStack.peek())){
                double second = numberStack.pop();
                double first = numberStack.pop();
                Operators op = operatorStack.pop();
                double result = applyOperator(first, op, second);
                if(Double.isInfinite(result)) {
                	throw new ArithmeticException("/ by zero");
                }
                numberStack.push(result);
            } else{
                break;
            }
        }
    }
    
    public enum Operators{ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK}

    private double applyOperator(double left, Operators op, double right){
        switch (op){
            case ADD: return left + right;
            case SUBTRACT: return left - right;
            case MULTIPLY: return left * right;
            case DIVIDE: return left / right;
            default: return right;
        }
    }

    private int operatorPriority(Operators op){
        switch (op){
            case ADD: return 1;
            case SUBTRACT: return 1;
            case MULTIPLY: return 2;
            case DIVIDE: return 2;
            case BLANK: return 0;
        }
        return 0;
    }
    
    private double[] parseNumber(String sequence, int offset){
        StringBuilder sb = new StringBuilder();
        while(offset < sequence.length() && (Character.isDigit(sequence.charAt(offset)) || sequence.charAt(offset)=='.')){
            sb.append(sequence.charAt(offset));
            offset++;
        }
        double number = Double.parseDouble(sb.toString());
        double double_array[] = new double[2];
        double_array[0] = number;
        double_array[1] = offset;
        return double_array;
    }

    private Operators parseOperator(String sequence, int offset){
        if(offset < sequence.length()){
            char op = sequence.charAt(offset);
            switch (op){
                case '+': return Operators.ADD;
                case '-': return Operators.SUBTRACT;
                case '*': return Operators.MULTIPLY;
                case '/': return Operators.DIVIDE;
            }
        }
        return Operators.BLANK;
    }
}
