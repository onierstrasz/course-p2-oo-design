package tictactoe.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tictactoe.Move;


/**
 * Interface to allow remote clients to be notified
 * of updates from the game.  This is the only interface
 * the client exports to the server.
 * @author $Author: oscar $
 * @version $Id: RemoteObserver.java 16531 2008-02-28 15:58:20Z oscar $
 */
public interface RemoteObserver extends Remote {
	public void update(Move move) throws RemoteException;
}
