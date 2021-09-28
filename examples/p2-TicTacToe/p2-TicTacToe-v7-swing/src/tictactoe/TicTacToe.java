package tictactoe;

/**
 * Concrete subclass of AbstractBoardGame.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16615 2008-03-01 17:08:43Z oscar $
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
