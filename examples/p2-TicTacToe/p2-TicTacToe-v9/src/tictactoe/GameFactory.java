package tictactoe;

import tictactoe.gui.GUIplayer;

/**
 * Abstract superclass of TicTacToeFactory and GomokuFactory
 * @author $Author: oscar $
 * @version $Id: GameFactory.java 16609 2008-03-01 16:29:27Z oscar $
 */
public abstract class GameFactory {
	protected BoardGame game = null;

	/**
	 * @return a game with GUIplayers bound.
	 */
	public BoardGame getGame() {
		BoardGame gameToJoin;
		if (game == null) {
			Player X = new GUIplayer('X');
			Player O = new GUIplayer('O');
			game = makeGame(X,O);
			gameToJoin = game;
		} else {
			gameToJoin = game;
			game = null;
		}
		return gameToJoin;
	}
	
	/**
	 * Subclasses implement the actual factory method.
	 */
	protected abstract BoardGame makeGame(Player X, Player O);
}
