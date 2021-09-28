package tictactoe.server;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;

import tictactoe.*;
import tictactoe.rmi.*;

/**
 * Implements factory method to instantiate BoardGame
 * for remote clients.
 * @author $Author: oscar $
 * @version $Id: GameServer.java 16609 2008-03-01 16:29:27Z oscar $
 */
@SuppressWarnings("serial")
public class GameServer
extends UnicastRemoteObject
implements RemoteGameServer
{
	protected GameFactory tictactoeFactory, gomokuFactory;

	/**
	 * main() method to create one instance of GameFactory
	 * and register it with the Naming service.
	 * @param args server:port (e.g., asterix.unibe.ch:2001)
	 * @throws RemoteException
	 * @throws MalformedURLException
	 **/
	public static void main(String[] args)
		throws RemoteException, MalformedURLException
	{
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Set new Security manager");
		} else {
			System.out.println("Security manager already set");
		}
		
		if (args.length != 1) {
			System.err.println("Usage: java "
					+ "tictactoe.server.GameServer"
					+ " <RMI registry host:port address>");
			System.exit(1);
		}
		
		String name = "//" + args[0] + "/GameServer";
		
		// throws RemoteException
		RemoteGameServer server = new GameServer();
		System.out.println("Binding " + name + " ...");
		// thows MalformedURLException
		Naming.rebind(name, server);
		System.out.println("New GameServer registered");
	}

	public GameServer() throws RemoteException {
		super();
		tictactoeFactory = new TicTacToeFactory();
		gomokuFactory = new GomokuFactory();
	}
	
	/**
	 * Returns a RemoteGame instance for a remote client.
	 * If a game already exists, the client joins the
	 * existing game.  Else a new game is made.
	 *<P>
	 * This method is synchronized since multiple clients
	 * may try to connect concurrently.
	 */
	public synchronized RemoteGame joinTicTacToe() throws RemoteException {
		return new GameProxy(tictactoeFactory.getGame());
	}
	
	public synchronized RemoteGame joinGomoku() throws RemoteException {
		return new GameProxy(gomokuFactory.getGame());
	}

}
