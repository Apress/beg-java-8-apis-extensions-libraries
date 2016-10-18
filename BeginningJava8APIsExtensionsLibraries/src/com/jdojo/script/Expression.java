// Expression.java
package com.jdojo.script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptContext;

public class Expression {
	private String exp;
	private ScriptContext context;

	private String op1;
	private char op1Sign = '+';

	private String op2;
	private char op2Sign = '+';

	private char operation;

	private boolean parsed;

	public Expression(String exp, ScriptContext context) {
		if (exp == null || exp.trim().equals("")) {
			throw new IllegalArgumentException(this.getErrorString());
		}
		this.exp = exp.trim();

		if (context == null) {
			throw new IllegalArgumentException("ScriptContext cannot be null.");
		}
		this.context = context;
	}

	public String getExpression() {
		return exp;
	}

	public ScriptContext getScriptContext() {
		return context;
	}

	public Double eval() {
		// Parse the expression
		if (!parsed) {
			this.parse();
			this.parsed = true;
		}

		// Extract the values for the operand		
		double op1Value = getOperandValue(op1Sign, op1);
		double op2Value = getOperandValue(op2Sign, op2);

		// Evaluate the expression
		Double result = null;
		switch (operation) {
			case '+':
				result = op1Value + op2Value;
				break;
			case '-':
				result = op1Value - op2Value;
				break;
			case '*':
				result = op1Value * op2Value;
				break;
			case '/':
				result = op1Value / op2Value;
				break;
			default:
				throw new RuntimeException("Invalid operation:" + operation);
		}
		return result;
	}

	private double getOperandValue(char sign, String operand) {
		// Check if operand is a double
		double value;
		try {
			value = Double.parseDouble(operand);
			return sign == '-' ? -value : value;
		} 
		catch (NumberFormatException e) {
			// Ignore it. Operand is not in a format that can be
			// converted to a double value.			
		}

		// Check if operand is a bind variable
		Object bindValue = context.getAttribute(operand);
		if (bindValue == null) {
			throw new RuntimeException(operand	+ " is not found in the script context.");
		}

		if (bindValue instanceof Number) {
			value = ((Number) bindValue).doubleValue();
			return sign == '-' ? -value : value;
		} 
		else {
			throw new RuntimeException(operand	+ " must be bound to a number.");
		}
	}

	public void parse() {
		// Supported expressiona are of the form v1 op v2, where v1 and v2
		// are variable names or numbers, and op could be +, -, *, or /

		// Prepare the pattern for the expected expression
		String operandSignPattern = "([+-]?)";
		String operandPattern = "([\\p{Alnum}\\p{Sc}_.]+)";
		String whileSpacePattern = "([\\s]*)";
		String operationPattern = "([+*/-])";
		String pattern = "^" + operandSignPattern + operandPattern +
			whileSpacePattern + operationPattern + whileSpacePattern +
			operandSignPattern + operandPattern + "$";

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(exp);
		if (!m.matches()) {
			// The expression is not in the expected format
			throw new IllegalArgumentException(this.getErrorString());
		}

		// Get operand-1
		String temp = m.group(1);
		if (temp != null && !temp.equals("")) {
			this.op1Sign = temp.charAt(0);
		}
		this.op1 = m.group(2);

		// Get operation
		temp = m.group(4);
		if (temp != null && !temp.equals("")) {
			this.operation = temp.charAt(0);
		}

		// Get operand-2
		temp = m.group(6);
		if (temp != null && !temp.equals("")) {
			this.op2Sign = temp.charAt(0);
		}
		this.op2 = m.group(7);
	}

	private String getErrorString() {
		return "Invalid expression[" + exp + "]" +
			"\nSupported expression syntax is: op1 operation op2" +
			"\n where op1 and op2 can be a number or a bind variable" +
			" , and operation can be +, -, *, and /.";
	}

	@Override
	public String toString() {
		return "Expression: " + this.exp + ", op1 Sign = " +
			op1Sign + ", op1 = " + op1 + ", op2 Sign = " +
			op2Sign + ", op2 = " + op2 + ", operation = " + operation;
	}
}
