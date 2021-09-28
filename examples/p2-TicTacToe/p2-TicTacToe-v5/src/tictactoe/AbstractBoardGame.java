package tictactoe;
import java.io.IOException;

/**
 * AbstractBoardGame implements common methods to
 * TicTacToe and Gomoku.
 * @author $Author: oscar $
 * @version $Id: AbstractBoardGame.java 16604 2008-03-01 15:19:23Z oscar $
 */
public abstract class AbstractBoardGame implements BoardGame {
	static final int X = 0;
	static final int O = 1;

	protected int rows;
	protected int cols;
	protected int winningScore;

	protected char[][] gameState;	
	protected Player winner = new Player(); // nobody
	protected Player[] player;
	protected int turn = X; // initial turn
	protected int squaresLeft;

	/**
	 * The state of the game is represented as 3x3
	 * array of chars marked ' ', 'X', or 'O'.
	 * We index the state using chess notation,
	 * i.e., column is 'a' through 'c' and row is
	 * '1' through '3'.
	 */
	public AbstractBoardGame(Player playerX, Player playerO)
	{
		player = new Player[2];
		player[X] = playerX;
		player[O] = playerO;
		this.init();
		squaresLeft = rows * cols;
		gameState = new char[rows][cols];
		for (int col=0; col <cols; col++)
			for (int row=0; row<rows; row++)
				this.set(col,row,' ');
	}
	
	/**
	 * Subclasses should initialize rows, cols
	 * and winningScore
	 */
	protected abstract void init();

	/**
	 * set() and get() translate between chess coordinates
	 * and array indices.
	 * NB: Use package scope.
	 */
	protected void set(int col, int row, char mark)
	{
		assert inRange(col, row);
		gameState[col][row] = mark;
	}

	protected char get(int col, int row)
	{
		assert inRange(col, row);
		return gameState[col][row];
	}
	
	/**
	 * The game is not over as long as there is no winner
	 * and somebody can still make a move ...
	 */
	public boolean notOver() {
		return this.winner().isNobody()
		&& this.squaresLeft() > 0;
	}
	
	/**
	 * A plain ascii representation of the game,
	 * mainly for debugging purposes.
	 */
	public String toString() {
		StringBuffer rep = new StringBuffer();
	
		for (int row=this.rows-1; row>=0; row--) {
			rep.append(1 + row);
			if (row < 9)
				rep.append(' '); // extra space for single digit
			rep.append("  ");
			for (int col=0; col < this.cols; col++) {
				rep.append(this.get(col,row));
				if (col<this.cols-1) {
					rep.append(" | ");
				}
			}
			rep.append('\n');
			if (row>0) {
				rep.append("   ---");
				for (int i=1; i<this.cols; i++)
					rep.append("+---");
				rep.append("\n");
			}
		}
		rep.append(" ");
		for (int col=0; col < this.cols; col++) {
			rep.append("   ");
			rep.append((char) ('a' + col));
		}
		rep.append("\n");
		return(rep.toString());
	}
	
	/**
	 * Needed for getter and setter preconditions.
	 * Reports true if coordinates are valid.
	 */
	protected boolean inRange(int col, int row) {
		return ((0<=col) && (col<this.cols)
			&& (0<=row) && (row<this.rows));
	}

	/**
	 * Called by the current player during an update().
	 * The player attempts to put a mark at a location.
	 */
	public void move(String coord, char mark)
	{
		assert this.notOver();
		int col = getCol(coord);
		int row = getRow(coord);
		assert this.get(col, row) == ' ';
		this.set(col, row, mark);
		this.squaresLeft--;
		this.swapTurn();
		this.checkWinner();
		assert this.invariant();
	}

	/**
	 * Extract the column letter from a String coordinate
	 * and convert to an index.
	 * E.g., "b17" -> 1
	 */
	protected int getCol(String coord)
	{
		assert coord != null;
		assert coord.length() > 0;
		char col = coord.charAt(0);
		assert (col>='a') && (col<='z');
		return col - 'a';
	}
	
	/**
	 * Extract the column digits from a String coordinate
	 * and convert to an index.
	 * E.g., "b17" -> 16
	 */
	protected int getRow(String coord)
	{
		assert coord != null;
		assert coord.length() > 1;
		try {
			int row = Integer.parseInt(coord.substring(1));
			return row - 1;
		} catch (NumberFormatException err) {
			throw new AssertionError(err.getMessage());
		}
	}

	/**
	 * Ask the current player to make a move.
	 */
	public void update() throws IOException {
		this.player[turn].move(this);
	}

	public Player currentPlayer() {
		return player[turn];
	}

	public Player winner() {
		return this.winner;
	}
	
	public int squaresLeft() {
		return this.squaresLeft;
	}
	
	protected void swapTurn() {
		turn = (turn == X) ? O : X;
	}
	
	/**
	 * Check for a winning row, column or diagonal.
	 * (This code smells!  How can we clean it up?)
	 */
	protected void checkWinner()
	{
		char player;
		for (int row=2; row>=0; row--) {
			player = this.get(0,row);
			if (player == this.get(1,row)
				&& player == this.get(2,row)) {
				this.setWinner(player);
				return;
			}
		}
		for (int col=0; col <=2; col++) {
			player = this.get(col,0);
			if (player == this.get(col,1)
				&& player == this.get(col,2)) {
				this.setWinner(player);
				return;
			}
		}
		player = this.get(1,1);
		if (player == this.get(0,0)
			&& player == this.get(2,2)) {
				this.setWinner(player);
				return;
		}
		if (player == this.get(0,2)
			&& player == this.get(2,0)) {
				this.setWinner(player);
				return;
		}
	}
		
	/**
	 * Look up which player is the winner, and set winner
	 * accordingly. Hm. Maybe we should store Players
	 * instead of chars in our array!
	 */
	protected void setWinner(char aPlayer) {
		if (aPlayer == ' ')
			return;
		if (aPlayer == player[X].mark())
			winner = player[X];
		else
			winner = player[O];
	}
	
	/**
	 * These seem obvious, which is exactly why
	 * they should be checked.
	 */
	protected boolean invariant() {
		return (turn == X || turn == O)
			&& (this.notOver() 
				|| this.winner() == player[X]
				|| this.winner() == player[O]
				|| this.winner().isNobody())
			&& (squaresLeft < 9
				// else, initially:
				|| turn == X && this.winner().isNobody());
	}
}
