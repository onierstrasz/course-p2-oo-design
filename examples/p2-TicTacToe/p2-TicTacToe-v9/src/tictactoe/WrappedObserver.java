package tictactoe;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import tictactoe.rmi.RemoteObserver;

/**
 * Wraps a RemoteObserver to look like a regular
 * Observer (which does <I>not</I> throw a
 * RemoteException).
 *<P>
 * This allows AbstractBoardGame to extend
 * Observable without having to adapt any
 * of its methods.
 *
 * @author $Author: oscar $
 * @version $Id: WrappedObserver.java 16609 2008-03-01 16:29:27Z oscar $
 **/
class WrappedObserver implements Observer {
	protected RemoteObserver remote;
	WrappedObserver(RemoteObserver ro) {
		remote = ro;
	}
	
	/**
	 * Updates the RemoteObservable in a separate
	 * Thread and ignores RemoteExceptions so that
	 * the server will not block.
	 */
	public void update(Observable o, Object arg) {
		// Only final variables can be passed to an inner class
		final Move move = (Move) arg;
		Thread doUpdate = new Thread() {
			public void run() {
				try {
					remote.update(move);
				} catch(RemoteException err) {
					// just ignore error if remote client
					// cannot be notified
				}
			}
		};
		doUpdate.start();
	}
}
