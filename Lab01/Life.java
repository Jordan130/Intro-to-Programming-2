import java.util.*;

/**
 * This class creates a 2D array of boolean values and updates it based on the users values that they enter in the program.
 * @author Jordan Rios
 * @date 9/3/22
 * CSCI 162
 * Lab 01
 */
public class Life {
	
	/** This method accepts all the input from the user, as well as creates 2 different boolean 2D arrays.
	 * Then it has a for loop that prints out a new board 5 times.
	 * @param args does nothing
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int column = sc.nextInt();
		long seed = sc.nextLong();
		int birthLow = sc.nextInt();
		int birthHigh = sc.nextInt();
		int liveLow = sc.nextInt();
		int liveHigh = sc.nextInt();
		boolean[][] currentGen = new boolean[row][column]; //creates a 2D array
		boolean [][] nextGen = new boolean [row][column]; // creates a new 2D array so that we can store the previous values from
		fillWithRandom(currentGen, seed); // filling the inside of currentGen board with true or false values
		printBoard(currentGen); // prints the currentGen board using the printBoard method
		copyOfBoard(currentGen, nextGen); // creating a copy of the original currentGen board, and setting the values equal too nextGen
		System.out.println();
		for(int i = 0; i < 5; i++) { // prints out 5 game of life boards
			updateBoard(currentGen,nextGen, birthLow, birthHigh, liveLow, liveHigh); //updates currentGen and sets the values equal to the nextGen board
			copyOfBoard(nextGen, currentGen); // creates a copy of nextGen and sets the values the same as currentGen
			printBoard(currentGen); //prints out the currentGen that was just copied
			System.out.println();
		}
	}

	/** This method goes through the entire 2D array passed to it and fills the inside of with random true or false values using the seed given 
	 * @param board is sent from the main method as currentGen, and the nested for loop iterates over each index and places a true or false value with it 
	 * @param seed is sent from the main method and its a random value to fill in the middle
	 */
	public static void fillWithRandom(boolean[][] board, long seed) { 
		Random numGenerator = new Random(seed);													
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				board[i][j] = numGenerator.nextBoolean();
			}
		}
	}
	
	/** All this method does is prints a 2D array that is passed to it
	 * @param board is the 2D array that you want to print out
	 */
	public static void printBoard(boolean [][] board) { 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == true) {
					System.out.print("#" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}
	
	/** This method updates the currentGen board and sets the values equal to the nextGen board if it meets the right conditions
	 * @param currentGen a 2D array of the first game of life board
	 * @param nextGen a 2D array of the game of life board that you want to have the same values as the currentGen board
	 * @param birthLow the minimum number a cell needs to survive the next term if its already dead
	 * @param birthHigh the maximum number a cell needs to survive the next term if its already dead
	 * @param liveLow the minimum number a cell needs to survive the next term if its already alive
	 * @param liveHigh the maximum number a cell needs to survive the next term if its already alive
	 */
	public static void updateBoard(boolean[][] currentGen, boolean [][] nextGen, int birthLow, int birthHigh, int liveLow, int liveHigh) {
		
		for (int row = 1; row < currentGen.length - 1; row++) {
			for (int col = 1; col < currentGen[row].length - 1; col++) {
				int neighbors = countNeighbor(currentGen, row, col); // counts the neighbors of each cell in the currentGen board
				//if the cell is alive and is in the liveLow and liveHigh range, then it updates nextGen to true on the next term
				if ((currentGen[row][col] == true) && (neighbors >= liveLow && neighbors <= liveHigh)) {                   
					nextGen[row][col] = true;
				}
				//if the cell is alive and is outside the liveLow and liveHigh range, then it updates nextGen to false on the next term
				else if ((currentGen[row][col] == true) && (neighbors < liveLow || neighbors > liveHigh)){
					nextGen[row][col] = false;
				//if the cell is dead and is in the birthLow and birthHigh range, then it updates nextGen to true on the next term
				}
				if((currentGen[row][col] == false) && (neighbors >= birthLow && neighbors <= birthHigh)){
					nextGen[row][col] = true;
				//if the cell is dead and is outside the liveLow and liveHigh range, then it updates nextGen to false on the next term
				}
				else if ((currentGen[row][col] == false) && (neighbors < liveLow || neighbors > liveHigh )){
					nextGen[row][col] = false;
				}
			}
		}
	}
	
	/** This method creates a copy of a board passed to it, and sets the values equal in the new copy from the old board
	 * @param board this 2D array is being checked by the nested for loop and its values are being set to the new 2D array called copy
	 * @param copy this 2D array has the same exact values as the original one passed to this method
	 */
	public static void copyOfBoard(boolean board[][], boolean copy[][]) { // create a copy of the board																						
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				copy[i][j] = board[i][j];
			}
		}
	}

	/** This method counts the number of cells around each cell on the inside part of the board.
	 * The for loop checks each value surrounding a cell and if its true then it adds one to the neighbors
	 * Then once it has the number of cells counted, it is returned from this method to the updateBoard method.
	 * @param board the boolean 2D array in which this for loop checks and counts how many neighbors each cell has 
	 * @param row the number of rows the 2D array has
	 * @param col the number of columns the 2D array has
	 * @return the neighbors is returned from this method to updateBoard so that it can run through all of the conditions
	 */
	public static int countNeighbor(boolean[][] board, int row, int col) {
		int neighbors = 0;
		for (int i = row - 1; i <= row + 1; i++) { 
			for (int j = col - 1; j <= col + 1; j++) {
				if(board[i][j] == true) {
					neighbors++;
				}
			}
		}
		return neighbors;
	}
}
