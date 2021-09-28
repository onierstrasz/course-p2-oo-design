package tictactoe;

/**
 * Concrete subclass of AbstractBoardGame.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16609 2008-03-01 16:29:27Z oscar $
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
	
	/**
	 * The name of the game
	 */
	public String name() {
		return "Tic Tac Toe";
	}
}
