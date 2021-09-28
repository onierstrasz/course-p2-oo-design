package tictactoe.gui;

import java.awt.event.*;
import java.rmi.RemoteException;

import tictactoe.Move;
import tictactoe.rmi.RemoteGame;

/**
 * This class picks up mouse clicks and propogates them
 * to a Player of the BoardGame.
 *
 * @author $Author: oscar $
 * @version $Id: PlaceListener.java 16609 2008-03-01 16:29:27Z oscar $
 */
public class PlaceListener extends MouseAdapter {
	protected final Place place;
	protected final GameGUI gui;
	
	public PlaceListener(Place myPlace, GameGUI myGui) {
		place = myPlace;
		gui = myGui;
	}

	/**
	 * When the mouse is clicked, forward a Move request to the
	 * RemoteGame.  We also report when the game is over.
	 **/
	public void mouseClicked(MouseEvent e) {
		RemoteGame game = gui.getGame();
		int col = place.getCol();
		int row = place.getRow();
		message("Got a mouse click");
		try {
			if (game.ready()) {
				if (game.notOver()) {
					Move move = new Move(col,row, gui.getMark());
					// Provide some feedback, in case the server is slow
					message("Attempting " + move);
					
					if (!game.move(move))
						message("Invalid move! -- "
							+ game.currentPlayer() + " plays");
					// else game will call us back with an update()
				} else {
					message("The game is over!");
				}
			} else {
				// We are missing a way to inform the user that
				// another player has connected!
				message("Please wait for a second player!");
			}
		} catch (RemoteException err) {
			// The server has crashed?
			message("Can't invoke remote game methods!" + err);
		}
	}

	protected void message(String s) {
		gui.showFeedBack(s);
	}
}

