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
 * @version $Id: GameGUI.java 16607 2008-03-01 15:27:24Z oscar $
 */
@SuppressWarnings("serial")
public class GameGUI extends Frame implements Observer {

	protected BoardGame game;
	protected Label label;
	protected Place[][] places;
	
	static protected final int MINSIZE = 50;

	public GameGUI(String title) throws HeadlessException {
		super(title);
		game = makeGame();
		game.addObserver(this);
		this.setSize(MINSIZE*game.getCols(),
				MINSIZE*(1+game.getRows()));
		// setLayout(new BorderLayout()); // by default
		add("North", makeControls());
		add("Center", makeGrid());
		label = new Label();
		add("South", label);
		showFeedBack(game.currentPlayer().mark() + " plays");
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				GameGUI.this.dispose();
			}
		});
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameGUI("TicTacToe");
	}

	public BoardGame getGame() { return game; }
	
	protected BoardGame makeGame() {
		Player X = new GUIplayer('X');
		Player O = new GUIplayer('O');
		return new TicTacToe(X, O);
		// return new Gomoku(X, O);
	}
	
	protected Component makeControls() {
		Button again = new Button("New game");
		again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFeedBack("starting new game ...");
				newGame();
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
				Place p = new Place(col, row);
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
		game = makeGame();
		game.addObserver(this);

		int cols = game.getCols();
		int rows = game.getRows();

		for (int col=cols-1; col>=0; col--) {
			for (int row=0; row<rows; row++) {
				places[col][row].clearImage();
			}
		}
		showFeedBack(game.currentPlayer().mark() + " plays");
	}

	/**
	 * Displays a feedback string in the associated Label component.
	 * @param msg the string to display
	 */
	public void showFeedBack(String msg) {
		label.setText(msg);
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
		showFeedBack("got an update: " + move);
		places[move.col][move.row].setMove(move.player);
	}
}

