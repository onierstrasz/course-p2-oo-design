package tictactoe;

/**
 * Class to enforce rules of TicTacToe.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16592 2008-03-01 14:07:41Z oscar $
 */
public class TicTacToe {
	protected char[][] gameState;

	/**
	 * The state of the game is represented as 3x3
	 * array of chars marked ' ', "x", or 'O'.
	 * We index the state using chess notation,
	 * i.e., column is 'a' through 'c' and row is
	 * '1' through '3'.
	 */
	public TicTacToe() {
		gameState = new char[3][3];
		for (char col='a'; col <='c'; col++)
			for (char row='1'; row<='3'; row++)
				this.set(col,row,' ');
	}

	/**
	 * set() and get() translate between chess coordinates
	 * and array indices.
	 * NB: Use package scope.
	 */
	void set(char col, char row, char mark) {
		assert inRange(col, row);
		gameState[col-'a'][row-'1'] = mark;
	}

	char get(char col, char row) {
		assert inRange(col, row);
		return gameState[col-'a'][row-'1'];
	}

	/**
	 * No game logic yet, so game is over when we start.
	 */
	public boolean notOver() {
		return false;
	}

	/**
	 * A plain ascii representation of the game,
	 * mainly for debugging purposes.
	 */
	public String toString() {
		StringBuffer rep = new StringBuffer();
	
		for (char row='3'; row>='1'; row--) {
			rep.append(row);
			rep.append("  ");
			for (char col='a'; col <='c'; col++) {
				rep.append(this.get(col,row));
				if (col<'c') {
					rep.append(" | ");
				}
			}
			rep.append('\n');
			if (row>'1') {
				rep.append("  ---+---+---\n");
			}
		}
		rep.append("   a   b   c\n");
		return(rep.toString());
	}
	
	/**
	 * Needed for getter and setter preconditions.
	 * Reports true if coordinates are valid.
	 */
	boolean inRange(char col, char row) {
		return (('a'<=col) && (col<='c')
			&& ('1'<=row) && (row<='3'));
	}

}
