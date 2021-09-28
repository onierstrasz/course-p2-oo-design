package tictactoe;

/**
 * Concrete subclass of AbstractBoardGame.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16601 2008-03-01 15:14:25Z oscar $
 */
public class TicTacToe extends AbstractBoardGame {
	public TicTacToe(Player playerX, Player playerO)
	{
		super(playerX, playerO);
	}

	/* *
	 * Initialize state for TicTacToe.
	 */
	protected void init() {
		rows = cols = 3;
		winningScore = 3;
	}
}
