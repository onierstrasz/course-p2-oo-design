package tictactoe;

import tictactoe.gui.GUIplayer;

/**
 * @author $Author: oscar $
 * @version $Id: GameFactory.java 16608 2008-03-01 15:38:08Z oscar $
 */
public abstract class GameFactory {
	protected BoardGame gameToJoin = null;

	public BoardGame getGame() {
		BoardGame game;
		if (gameToJoin == null) {
			Player X = new GUIplayer('X');
			Player O = new GUIplayer('O');
			gameToJoin = makeGame(X,O);
			game = gameToJoin;
		} else {
			game = gameToJoin;
			gameToJoin = null;
		}
		return game;
	}
	
	protected abstract BoardGame makeGame(Player X, Player O);
}
