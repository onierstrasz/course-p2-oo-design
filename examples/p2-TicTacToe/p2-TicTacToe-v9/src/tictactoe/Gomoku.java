package tictactoe;

/**
 * @author $Author: oscar $
 * @version $Id: Gomoku.java 16609 2008-03-01 16:29:27Z oscar $
 */
public class Gomoku extends AbstractBoardGame {

	public Gomoku(Player X, Player O) {
		super(X,O);
	}

	/* *
	 * Initialize state for Gomoku.
	 */
	protected void init() {
		rows = cols = 10;
		winningScore = 5;
	}
	
	/**
	 * The name of the game.
	 */
	public String name() {
		return "Gomoku";
	}
}
