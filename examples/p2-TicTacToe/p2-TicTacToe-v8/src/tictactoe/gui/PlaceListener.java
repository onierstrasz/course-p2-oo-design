package tictactoe.gui;

import java.awt.event.*;
import tictactoe.BoardGame;
import tictactoe.InvalidMoveException;

/**
 * This class picks up mouse clicks and propogates them
 * to a Player of the BoardGame.
 *
 * @author $Author: oscar $
 * @version $Id: PlaceListener.java 16632 2008-03-01 22:39:44Z oscar $
 */
public class PlaceListener extends MouseAdapter {
	protected final Place place;
	protected final GameGUI gui;
	
	public PlaceListener(Place myPlace, GameGUI myGui) {
		place = myPlace;
		gui = myGui;
	}

	/**
	 * When the mouse is clicked, we ask the current player to
	 * make the corresponding move.  Report when the game is over.
	 */
	public void mouseClicked(MouseEvent e) {
		BoardGame game = gui.getGame();
		int col = place.getCol();
		int row = place.getRow();
		if (game.notOver()) {
			try {
				// ((GUIplayer) game.currentPlayer()).move(col,row);
				((GUIplayer) gui.getPlayer()).move(col,row);
				gui.showWhoPlays();
			} catch (AssertionError err) {
				gui.showFeedBack(err.getMessage());
			} catch (InvalidMoveException err) {
				gui.showFeedBack(err.getMessage());
			}
			if (!game.notOver()) {
				gui.showFeedBack("Game over -- " + game.winner() + " wins!");
			}
		} else {
			gui.showFeedBack("The game is over!");
		}
	}
}

