package tictactoe.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tictactoe.Move;


/**
 * Remote interface for TicTacToe and Gomoku.
 *
 * This interface adapts the BoardGame interface.
 * All RemoteGame methods throw RemoteException,
 * and none of them throws an InvalidMoveException..
 * BoardGame methods that return a Player are
 * adapted here so they return a char representing
 * the Player (since a Player is not a remote object).
 *
 * This interface also allows a client to register
 * as a RemoteObserver of the game.
 *
 * @author $Author: oscar $
 * @version $Id: RemoteGame.java 16609 2008-03-01 16:29:27Z oscar $
 */
public interface RemoteGame extends Remote {

	/**
	 * The game is ready to be played.
	 * (There are two connected clients.)
	 */
	public boolean ready()
		throws RemoteException;
	/**
	 * A client connected to the game must join
	 * the game to know what its mark is (X or O).
	 */
	public char join()
		throws RemoteException;
	/**
	 * Attempt to move to the given square.
	 * Called by a remote client.  The RemoteGame
	 * will check if move.mark corresponds to the
	 * current Player, and then call BoardGame.move().
	 *
	 * @return true if move is ok
	 */
	public boolean move(Move move)
		throws RemoteException;

	/**
	 * @return the number of columns of this game
	 */
	public int getCols()
		throws RemoteException;
	/**
	 * @return the number of rows
	 */
	public int getRows()
		throws RemoteException;
	/**
	 * @return the mark of the Player whose turn it is.
	 */
	public char currentPlayer()
		throws RemoteException;
	/**
	 * @return the name of winner of the game (possibly "nobody")
	 */
	public String winner()
		throws RemoteException;
	/**
	 * @return whether the game is over
	 */
	public boolean notOver()
		throws RemoteException;
	/**
	 * Register as a RemoteObserver of the game.
	 */
	public void addObserver(RemoteObserver o)
		throws RemoteException;
	/**
	 * Restart the game, initializing the state.
	 */
	public void restart()
		throws RemoteException;
	/**
	 * Return the name of the game.
	 */
	public String name()
		throws RemoteException;
}
