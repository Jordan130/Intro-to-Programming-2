import java.util.NoSuchElementException;

/**
 * A program that evaluates postfix expressions.
 * 
 * @author Jordan Rios
 */
public class PrefixEvaluator {

	/**
	 * Evaluates a mathematical expression.
	 * 
	 * @param input A string containing the expression
	 * @return A string containing the value of the expression, or an error message
	 *         if there was a problem
	 */
	public static String evaluate(String input) {
		if (input.isBlank() || input.isEmpty()) {
			return "No input.";
		}
		double number;
		TokenScanner sc = new TokenScanner(input);
		try {
			number = evaluateSub(sc);
			if(!sc.reachedEnd()) {
				throw new IllegalArgumentException("Computed answer, but not all input used.");
			}
		}
		catch(IllegalArgumentException exception) {
			return exception.getMessage();
		}
		catch(NoSuchElementException exception) {
			return exception.getMessage();
		}
		Token answer = new Token(number);
		return answer.toString();
	}

	/**
	 * Evaluates the next sub-expression found in a scanner.
	 * 
	 * @param scanner A TokenScanner containing at least one prefix (sub)-expression.
	 * @return The result of the first sub-expression found in the scanner.
	 * recursive case is when u see a symbol and base case is a number
	 */
	private static Double evaluateSub(TokenScanner scanner) {
		Double answer = 0.0;
		if(scanner.hasNextToken()) {
			Token token = scanner.nextToken();
			if(token.isLeftParen()){
				throw new IllegalArgumentException("( has no meaning here.");
			}
			else if(token.isRightParen()){
				throw new IllegalArgumentException(") has no meaning here.");
			}
			else if(token.isNumber()) {
				return token.getNumberValue();
			}
			else if(token.isOperator()) {
				char operator = token.getSymbol();
				if(operator == '+') {
					answer = evaluateSub(scanner) + evaluateSub(scanner);
				}
				else if(operator == '-') {
					answer = evaluateSub(scanner) - evaluateSub(scanner);
				}
				else if(operator == '*') {
					answer = evaluateSub(scanner) * evaluateSub(scanner);
				}
				else {
					answer = evaluateSub(scanner) / evaluateSub(scanner);
				}
			}
		}
		else {
			throw new NoSuchElementException("Not enough operands.");
		}
		return answer;
	}
	
}
