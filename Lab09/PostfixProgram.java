import java.util.Scanner;

/**
 * A program that evaluates postfix expressions.
 * 
 * @author Chad Hogg
 */
public class PostfixProgram {

	/**
	 * Runs the program.
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter an expression: ");
		String expression = console.nextLine();
		String result = PostfixEvaluator.evaluate(expression);
		System.out.println(result);
	}
}
