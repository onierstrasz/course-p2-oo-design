package tictactoe;

import java.util.Observable;

/**
 * AbstractBoardGame implements common methods to
 * TicTacToe and Gomoku.
 * @author $Author: oscar $
 * @version $Id: AbstractBoardGame.java 16629 2008-03-01 22:34:04Z oscar $
 */
public abstract class AbstractBoardGame
	extends Observable implements BoardGame
{
	static final int X = 0;
	static final int O = 1;

	protected int rows;
	protected int cols;
	protected int winningScore;

	protected Player[][] gameState;	
	protected final Player nobody = new InactivePlayer();
	protected Player winner = nobody;
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
		playerX.setGame(this);
		playerO.setGame(this);
		
		this.init();
		
		squaresLeft = this.squares();
		gameState = new Player[rows][cols];
		for (int col=0; col <cols; col++)
			for (int row=0; row<rows; row++)
				this.set(col,row,nobody);
	}
	
	/**
	 * Subclasses should initialize rows, cols
	 * and winningScore
	 */
	protected abstract void init();

	/**
	 * @return Returns the cols.
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * @return Returns the rows.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Modifies the state of the game.  This method is only called
	 * by the BoardGame itself when a valid move has taken place.
	 * Any Observers are notified that the state has changed.
	 *
	 * @param col the column
	 * @param row the row
	 * @param player the Player attempting the move
	 */
	protected void set(int col, int row, Player player)
	{
		assert inRange(col, row);
		gameState[col][row] = player;
	}

	public Player get(int col, int row)
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
				rep.append(this.get(col,row).mark());
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
	public boolean inRange(int col, int row) {
		return ((0<=col) && (col<this.cols)
			&& (0<=row) && (row<this.rows));
	}

	/**
	 * Called by the current player.  Here we check if the move
	 * is valid.  If so the game state is updated and the
	 * Observers are notified by calling this.set().
	 */
	public void move(int col, int row, Player p) throws InvalidMoveException
	{
		assert this.notOver();
		assert p == currentPlayer();
		checkInput(this.get(col, row).isNobody(), "That square is occupied!");
		this.set(col, row, p);
		this.squaresLeft--;
		this.swapTurn();
		this.checkWinner(col,row);
		assert this.invariant();
		setChanged();
		notifyObservers(new Move(col, row, p));
	}

	protected void checkInput(Boolean condition, String message) throws InvalidMoveException {
		if (!condition) {
			throw new InvalidMoveException(message);
		}
	}

	/**
	 * Extract the column letter from a String coordinate
	 * and convert to an index.
	 * E.g., "b17" -> 1
	 * NB: package scope for testing
	 */
	int getCol(String coord)
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
	 * NB: package scope for testing
	 */
	int getRow(String coord)
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
	 * New algorithm needed for larger boards.
	 * Create a "Runner" that starts at the current position,
	 * and runs back in forth in a given direction, returning
	 * the length of the run (i.e., number of consecutive pieces
	 * of the same Player).  If the number is big enough, the
	 * current Player wins.
	 */
	protected void checkWinner(int col, int row)
	{
		Player player = this.get(col,row);
		Runner runner = new Runner(this, col, row);

		// check vertically
		if (runner.run(0,1) >= this.winningScore)
			{ this.setWinner(player); return; }
		// check horizontally
		if (runner.run(1,0) >= this.winningScore)
			{ this.setWinner(player); return; }
		// check diagonally
		if (runner.run(1,1) >= this.winningScore)
			{ this.setWinner(player); return; }
		// and check the other diagonal
		if (runner.run(1,-1) >= this.winningScore)
			{ this.setWinner(player); return; }
	}
		
	protected void setWinner(Player aPlayer) {
		winner = aPlayer;
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
			&& (squaresLeft < this.squares()
				// else, initially:
				|| turn == X && this.winner().isNobody());
	}
	
	/**
	 * @return total number of squares
	 * Package scope for testing.
	 */
	int squares() {
		return rows * cols;
	}
}
