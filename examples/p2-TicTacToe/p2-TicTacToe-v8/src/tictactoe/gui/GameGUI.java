package tictactoe.gui;

import tictactoe.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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
 * @version $Id: GameGUI.java 16632 2008-03-01 22:39:44Z oscar $
 */
@SuppressWarnings("serial")
public class GameGUI extends Frame implements Observer {
	protected final String imageDir = "tictactoe/gui/images/";

	protected BoardGame game;
	protected Label label;
	protected Place[][] places;
	protected Image[] images;
	protected Player player;
	
	static protected final int MINSIZE = 50;

	public GameGUI(BoardGame myGame)
		throws HeadlessException
	{
		super(myGame.name());
		images = this.getImages();
		game = myGame;
		game.addObserver(this);
		player = myGame.join();
		this.setSize(MINSIZE*game.getCols(),
				MINSIZE*(1+game.getRows()));
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
	}
	
	public static void main(String[] args) {
		// We don't start from this class anymore.
		new GameConsole("TicTacToe/Gomoku Console");
	}

	/**
	 * Get the X and O images from the jar file.
	 */
	protected Image[] getImages() {
		Image[] images = new Image[3];
		images[0] = getImage("cross.gif");
		images[1] = getImage("not.gif");
		images[2] = getImage("blank.gif");
		return images;
	}

	/*
	 * other version
	protected Image getImage(String imageFile){
		URL url = getClass().getResource(imageDir + imageFile);
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		assert image != null;
		return image;
	}
	*/
	
	protected Image getImage(String imageFile){
		ClassLoader cl = this.getClass().getClassLoader();
		Image image = Toolkit.getDefaultToolkit().createImage(cl.getResource(imageDir + imageFile));
		assert image != null;
		return image;
	}
		
	public BoardGame getGame() { return game; }
	
	public Player getPlayer() { return player; }
	
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
	 */
	protected Component makeGrid() {
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
		game.restart();
	}

	/**
	 * Displays a feedback string in the associated Label component.
	 * @param msg the string to display
	 */
	public void showFeedBack(String msg) {
		label.setText(msg);
	}
	
	public void showWhoPlays() {
		showFeedBack(game.currentPlayer().mark() + " plays");
	}

	/**
	 * Implements the java.util.Observer interface.
	 * Called by the BoardGame when its state changes.
	 * @param o an instance of a BoardGame
	 * @param arg an instance of Move
	 * @see Move
	 */
	public void update(Observable o, Object arg) {
		Move move = (Move) arg;
		// showFeedBack("got an update: " + move);
		places[move.col][move.row].setMove(move.player);
		showWhoPlays();
	}
}

