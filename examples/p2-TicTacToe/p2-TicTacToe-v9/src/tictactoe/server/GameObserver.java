package tictactoe.server;

import java.rmi.*;
import java.rmi.server.*;

import tictactoe.Move;
import tictactoe.gui.GameGUI;
import tictactoe.rmi.*;

/**
 * Called by the RemoteGame when it changes state.
 * This is the only Remote object exported by the
 * client to the server.
 *
 * GameObserver is a separate entity since GameView cannot
 * also be a Remote object.
 *
 * @author $Author: oscar $
 * @version $Id: GameObserver.java 16609 2008-03-01 16:29:27Z oscar $
 **/
@SuppressWarnings("serial")
public class GameObserver
	extends UnicastRemoteObject
	implements RemoteObserver
{
	protected GameGUI gui;

	/**
	 * Do not use this constructor.
	 * Must be declared since we inherit from UnicastRemoteObject.
	 */
	public GameObserver() throws RemoteException {
		super();
	}

	/**
	 * This is the constructor to use.
	 */
	public GameObserver(GameGUI myGui) throws RemoteException {
		gui = myGui;
	}	

	/**
	 * Implements the RemoteObserver interface.
	 * Called by the BoardGame when its state changes.
	 *
	 * @param move an instance of Move
	 */
	public void update(Move move) throws RemoteException {
		gui.update(move);
	}
}
