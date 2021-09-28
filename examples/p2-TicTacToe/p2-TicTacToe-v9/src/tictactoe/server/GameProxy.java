/*
 * Created on Mar 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tictactoe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tictactoe.*;
import tictactoe.rmi.*;

/**
 * BoardGame wrapper to provide RemoteGame interface.
 * NB: All methods are synchronized since multiple
 * client threads may access the server simultaneously.
 *
 * @author $Author: oscar $
 * @version $Id: GameProxy.java 16633 2008-03-01 22:47:44Z oscar $
 **/
@SuppressWarnings("serial")
public class GameProxy
	extends UnicastRemoteObject
	implements RemoteGame
{
	// the game we wrap
	protected BoardGame game;
	// number of clients connected
	// protected int connected = 0;
	
	/**
	 * Do not use this constructor.
	 * Must be declared since we inherit from UnicastRemoteObject
	 */
	GameProxy() throws RemoteException {
		super();
	}
	
	/**
	 * Use this constructor.  A GameProxy is just
	 * a wrapper around an existing BoardGame.
	 * Several methods are just boilerplate delegations
	 * to game.
	 */
	GameProxy(BoardGame myGame) throws RemoteException {
		game = myGame;
	}
	
	/**
	 * The game is ready to be played if
	 * there are two connected clients.
	 **/
	public synchronized boolean ready() throws RemoteException {
		return game.ready();
	}
	
	/**
	 * A client connected to the game must join
	 * the game to know what its mark is (X or O).
	 */
	public synchronized char join() throws RemoteException {
		return game.join().mark();
	}
	
	/**
	 * Attempt to move to the given square.
	 * Called by a remote client.
	 * We check if move.mark corresponds to the
	 * current Player, and then call BoardGame.move().
	 *
	 * @return true if the move is valid
	 */
	public synchronized boolean move(Move move)
		throws RemoteException
	{
		Player current = game.currentPlayer();
		if (current.mark() != move.mark)
			return false;
		try {
			game.move(move.col, move.row, current);
			return true;
		} catch (AssertionError e) {
			return false;
		} catch (InvalidMoveException e) {
			return false;
		}
	}
	
	/**
	 * For remote clients.
	 * @return mark of current player (i.e., not the Player)
	 **/
	public synchronized char currentPlayer() throws RemoteException {
		return game.currentPlayer().mark();
	}
	
	/**
	 * For remote clients.
	 * @return the name of the winner (i.e., not the Player)
	 **/
	public synchronized String winner() throws RemoteException
	{
		return game.winner().toString();
	}
	
	// The rest are boilerplate delegation methods
	
	public synchronized void addObserver(RemoteObserver remote) throws RemoteException {
		game.addObserver(remote);
	}
	
	public synchronized int getCols() throws RemoteException {
		return game.getCols();
	}
	
	public synchronized int getRows() throws RemoteException {
		return game.getRows();
	}
	
	public synchronized boolean notOver() throws RemoteException {
		return game.notOver();
	}
	
	public synchronized String name() throws RemoteException {
		return game.name();
	}
	
	public synchronized void restart() throws RemoteException {
		game.restart();
	}
}
