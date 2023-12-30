import java.util.*;

/**
 * This class creates a 2D array of boolean values and fills them with either dead or alive values.
 * @author Jordan Rios
 * @author Evan
 * @author Rick
 * @date 8/30/22
 * CSCI 162
 * Lab 00
 */
public class Life {
	
	/**
	 * Accepts 3 values and passes of type int and long and passes them to the method called board.
	 * @param args does nothing
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int column = sc.nextInt();
		long seed = sc.nextLong();
		board(row, column, seed);
	}
	
	/**
	 * This method creates a blank board full of boolean false values, and the nested loop prints out the 2D array of boolean values.
	 * @param row gets how many rows there are of the 2D array from the user 
	 * @param column gets how many columns there are of the 2D array from the user
	 * @param seed generates a random number
	 */
	public static void board(int row, int column, long seed) {
		Random numGenerator = new Random(seed);
		boolean[][] matrix = new boolean[row][column];
		fillWithRandom(matrix, numGenerator); //sending the matrix to this method because it will populate the middle of the board with either true or false values
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == true) { //technically do not need "== true" 
					System.out.print("#" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}	
	}
	
	/**
	 * This method creates a board with just the inner part, not the boarder, by using a nested for loop.
	 * @param board turns the board that was already full of false values, and assigns them values based on the random number
	 * @param generator is used to generate either a true or false values
	 */
	public static void fillWithRandom (boolean[][] board, Random generator ) {
		for(int i = 1; i < board.length - 1; i++) {
			for(int j = 1; j < board[i].length - 1; j++) {
				board[i][j] = generator.nextBoolean();
			}
		}
		
	}

}
