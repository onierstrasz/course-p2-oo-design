package tictactoe.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the GameServer
 * @author $Author: oscar $
 * @version $Id: RemoteGameServer.java 16531 2008-02-28 15:58:20Z oscar $
 */
public interface RemoteGameServer extends Remote {
	/**
	 * Returns RemoteGame instance for a remote client.
	 * If a game already exists, the client joins the
	 * existing game.  Else a new game is made.
	 **/
	public RemoteGame joinTicTacToe() throws RemoteException;
	public RemoteGame joinGomoku() throws RemoteException;
}
