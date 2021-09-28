package tictactoe;

/**
 * @author $Author: oscar $
 * @version $Id: TicTacToeFactory.java 16523 2008-02-28 14:58:55Z oscar $
 */
public class TicTacToeFactory extends GameFactory {
	protected BoardGame makeGame(Player X, Player O) {
		return new TicTacToe(X,O);
	}
}
