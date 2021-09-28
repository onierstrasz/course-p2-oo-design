package tictactoe.gui;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import tictactoe.rmi.RemoteGameServer;

/**
 * Top-level frame with buttons to start TicTacToe or Gomoku.
 * @author $Author: oscar $
 * @version $Id: GameConsole.java 16609 2008-03-01 16:29:27Z oscar $
 */
@SuppressWarnings("serial")
public class GameConsole extends Frame {
	static protected final String defaultHost = "localhost:2001";
	protected String host;
	protected RemoteGameServer server;
	
	/**
	 * @param title -- the name ofthe game
	 * @param myHost -- host and port number
	 */
	public GameConsole(String title, String myHost) throws HeadlessException {
		super(title);
		host = myHost;
		server = connectToServer();
		setLayout(new FlowLayout());
		add(makeTicTacToe());
		add(makeGomoku());
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				GameConsole.this.dispose();
				System.exit(0);
			}
		});
		this.setVisible(true);
		this.pack();
	}

	/**
	 * Assume default host & port: "localhost:2001"
	 */
	public GameConsole(String title) throws HeadlessException {
		this(title, defaultHost);
	}
	
	/**
	 * @param args -- host:port to override the default
	 */
	public static void main(String[] args) throws HeadlessException {
		if (args.length > 0) {
			new GameConsole("TicTacToe/Gomoku Console", args[0]);
		} else {
			new GameConsole("TicTacToe/Gomoku Console");
		}
	}
	
	/**
	 * @return a local TicTacToe GameGUI connected to a remote model
	 */
	protected Button makeTicTacToe() {
		Button button = new Button("Start TicTacToe");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					(new GameGUI(server.joinTicTacToe())).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		return button;
	}

	/**
	 * @return a local Gomoku GameGUI connected to a remote model
	 */
	protected Button makeGomoku() {
		Button button = new Button("Start Gomoku");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					(new GameGUI(server.joinGomoku())).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		return button;
	}
	
	/**
	 * Get a remote GameServer through an RMI registry.
	 */
	protected RemoteGameServer connectToServer() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Set new Security manager");
		} else {
			System.out.println("Security manager already set");
		}
		RemoteGameServer server = null;
		String name = "//" + host + "/GameServer";
		try {
				System.err.println("Looking up " + name + "...");
				server = (RemoteGameServer) Naming.lookup(name);
				System.err.println("Lookup succeeded!");
			} catch (Exception e) {
				System.err.println("Lookup failed: " + name);
				e.printStackTrace();
				System.exit(1);
			}
		return server;
	}
}
