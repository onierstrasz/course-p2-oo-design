package tictactoe;

/**
 * @author $Author: oscar $
 * @version $Id: GomokuFactory.java 16523 2008-02-28 14:58:55Z oscar $
 */
public class GomokuFactory extends GameFactory {
	protected BoardGame makeGame(Player X, Player O) {
		return new Gomoku(X,O);
	}
}
