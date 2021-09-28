package tictactoe;

/**
 * @author $Author: oscar $
 * @version $Id: Gomoku.java 16615 2008-03-01 17:08:43Z oscar $
 */
public class Gomoku extends AbstractBoardGame {

	public Gomoku(Player playerX, Player playerO) {
		super(playerX, playerO);
	}

	/* *
	 * Initialize state for Gomoku.
	 */
	protected void init() {
		rows = cols = 10;
		winningScore = 5;
	}
}
