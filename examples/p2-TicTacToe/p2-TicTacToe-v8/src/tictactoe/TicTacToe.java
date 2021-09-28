package tictactoe;

/**
 * Concrete subclass of AbstractBoardGame.
 * @author $Author: oscar $
 * @version $Id: TicTacToe.java 16608 2008-03-01 15:38:08Z oscar $
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
	
	public String name() {
		return "Tic Tac Toe";
	}
}
