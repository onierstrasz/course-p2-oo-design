package tictactoe.gui;

import tictactoe.*;
import tictactoe.rmi.RemoteGame;
import tictactoe.server.GameObserver;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

/**
 * This class implements the view on a TicTacToe game.
 * The Frame contains a grid of Place objects that
 * intercept mouse clicks and forward them to the game's
 * Players.  The Frame is also an Observer for the game.
 * When the game notifies the Frame of state change, it
 * updates the corresponding Place.  Below the grid there
 * is a Label component to display messages.
 *
 * @author $Author: oscar $
 * @version $Id: GameGUI.java 16626 2008-03-01 21:48:34Z oscar $
 */
@SuppressWarnings("serial")
public class GameGUI extends Frame {
	protected final String imageDir = "tictactoe/gui/images/";

	protected RemoteGame game;
	protected Label label;
	protected Place[][] places;
	protected Image[] images;
	protected char mark;
	
	// static protected final int MINSIZE = 20;
	static protected final int MINX = 200, MINY=220;

	public GameGUI(RemoteGame myGame)
		throws HeadlessException, RemoteException
	{
		super(myGame.name());
		images = this.getImages();
		game = myGame;
		try {
			game.addObserver(new GameObserver(this));
			mark = myGame.join();
			this.setSize(MINX,MINY);
			// setLayout(new BorderLayout()); // by default
			add("North", makeControls());
			add("Center", makeGrid());
			label = new Label();
			add("South", label);
			showWhoPlays();
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					GameGUI.this.dispose();
				}
			});
			this.setVisible(true);
		} catch (RemoteException e) {
			error("Can't invoke remote game methods: " + e);
		}
	}
	
	/**
	 * @deprecated Start from GameConsole instead.
	 */
	public static void main(String[] args) throws HeadlessException {
		new GameConsole("TicTacToe/Gomoku Console");
	}

	/**
	 * Get the X and O images from the file system.
	 */
	protected Image[] getImages() {
		Image[] images = new Image[3];
		images[0] = getImage("cross.gif");
		images[1] = getImage("not.gif");
		images[2] = getImage("blank.gif");
		return images;
	}

	protected Image getImage(String imageFile){
		ClassLoader cl = this.getClass().getClassLoader();
		Image image = Toolkit.getDefaultToolkit().createImage(cl.getResource(imageDir + imageFile));
		assert image != null;
		return image;
	}
	
	public RemoteGame getGame() { return game; }
	
	public char getMark() { return mark; }
	
	protected Component makeControls() {
		Button again = new Button("New game");
		again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFeedBack("starting new game ...");
				newGame();
				showWhoPlays();
			}
		});
		return again;
	}

	/**
	 * Make the grid that serves as a view and controller for the game.
	 * @throws RemoteException
	 */
	protected Component makeGrid() throws RemoteException {
		int cols = game.getCols();
		int rows = game.getRows();

		Panel grid = new Panel();
		grid.setLayout(new GridLayout(rows, cols));

		places = new Place[cols][rows];
		for (int row=rows-1; row>=0; row--) {
			for (int col=0; col<cols; col++) {
				// Place p = new Place(col, row, images);
				Place p = new Place(col, row, images);
				p.addMouseListener(new PlaceListener(p, this));
				grid.add(p);
				places[col][row] = p;
			}
		}
		return grid;
	}
	
	/**
	 * Start a new game by deleting the old one, instantiating
	 * a new one, and clearing the images of the Places.
	 */
	protected void newGame() {
		try {
			game.restart();
		} catch (RemoteException e) {
			error("Lost connection to game during newGame()! " + e);
		}
	}

	/**
	 * Displays a feedback string in the associated Label component.
	 * @param msg the string to display
	 */
	public void showFeedBack(String msg) {
		label.setText(msg);
	}
	
	public void showWhoPlays() {
		try {
			showFeedBack(game.currentPlayer() + " plays");
		} catch (RemoteException e) {
			error("Lost connection to game during showWhoPlays()! " + e);
		}
	}	

	/**
	 * Called by the GameObserver when the game's state changes.
	 *
	 * Note that GameView is <I>not</i> a Remote object.  Since
	 * Java supports only single inheritance, GameView cannot
	 * extend both Frame and UnicastRemoteObject.  To make the
	 * GameView accessible to the server, we must introduce a
	 * delegate GameObserver which is a remote object visible to
	 * the server, and which simply passes updates to the GameView.
	 *
	 * @param move an instance of Move
	 **/
	public void update(Move move)
	{
		places[move.col][move.row].setMove(move.mark);
		try {
			if (game.notOver()) {
				showFeedBack(game.currentPlayer() + " plays");
			} else {
				showFeedBack("Game over -- " + game.winner() + " wins!");
			}

		} catch (RemoteException e) {
			error("Lost connection to game during update()! " + e);
		}
	}
	
	/**
	 * Display a serious error.
	 **/
	protected void error(String s) {
		// Perhaps we should open a Dialog?
		System.err.println(s);
		showFeedBack(s);
	}
}

