package tictactoe;

/**
 * @author $Author: oscar $
 * @version $Id: Gomoku.java 16608 2008-03-01 15:38:08Z oscar $
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
	
	public String name() {
		return "Gomoku";
	}
}
