package tictactoe;

/**
 * Concrete subclass of AbstractBoardGame.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16598 2008-03-01 14:52:17Z oscar $
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
		rows = 3;
		cols = 3;
		winningScore = 3;
	}
}
