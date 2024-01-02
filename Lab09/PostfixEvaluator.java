import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * A program that evaluates postfix expressions.
 * 
 * @author Jordan Rios
 */
public class PostfixEvaluator {

	/**
	 * Evaluates a postfix mathematical expression.
	 * 
	 * @param input A string containing the expression.
	 * @return A string containing the value of the expression, or an error message
	 *         if there was a problem.
	 */
	public static String evaluate(String input) {
		TokenScanner expression = new TokenScanner(input);
		try {
			checkIfEmpty(expression);
		} catch (IllegalStateException exception) {
			return "No input.";
		}
		Stack<Double> stack = new Stack<>();
		while (expression.hasNextToken()) {
			Token s = expression.nextToken();
			if (s.isLeftParen()) {
				return "( has no meaning here.";
			}
			if (s.isRightParen()) {
				return ") has no meaning here.";
			}
			if (s.isNumber()) {
				double tokenNumValue = s.getNumberValue();
				stack.push(tokenNumValue);
			} else if (s.isOperator()) {
				char operator = s.getSymbol();
				double num1;
				double num2;
				try {
					num1 = stack.pop();
					num2 = stack.pop();
				} catch (EmptyStackException exception) {
					return "Stack underflow.  Not enough operands on stack.";
				}
				double answer = calculate(num1, num2, operator);
				stack.push(answer);
			}
		}
		try {
			tooMuchInput(expression);
		} catch (IllegalStateException exception) {
			return "Computed answer, but not all input used.";
		}

		double answer = stack.pop();
		try {
			numLeftOnStack(stack);
		} catch (IllegalStateException exception) {
			return "Computed answer, but values remain on stack.";
		}
		return Double.toString(answer);
	}

	/**
	 * A helper method that calculates the operation of two tokens.
	 * 
	 * @param num1     The first number.
	 * @param num2     The second number.
	 * @param operator What operation you want to perform with these 2 numbers.
	 * @return The answer.
	 */
	private static double calculate(double num1, double num2, char operator) {
		if (operator == '+') {
			return num2 + num1;
		} else if (operator == '-') {
			return num2 - num1;
		} else if (operator == '*') {
			return num2 * num1;
		} else {
			return num2 / num1;
		}
	}

	/**
	 * Checks whether or not the evaluation ended before the end of the input.
	 * 
	 * @param expression The expression you want to check.
	 */
	private static void tooMuchInput(TokenScanner expression) {
		if (!expression.reachedEnd()) {
			throw new IllegalStateException("Invalid.");
		}
	}

	/**
	 * Checks if the TokenScanner is empty.
	 * 
	 * @param expression The expression you want to check.
	 */
	private static void checkIfEmpty(TokenScanner expression) {
		if (!expression.hasNextToken()) {
			throw new IllegalStateException("Invalid.");
		}
	}

	/**
	 * Checks if there are values remaining on the stack after the input.
	 * 
	 * @param stack The stack you want to check to see if there's still any values in it.
	 */
	private static void numLeftOnStack(Stack<Double> stack) {
		if (!stack.isEmpty()) {
			throw new IllegalStateException("Invalid");
		}
	}

}
