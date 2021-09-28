package tictactoe;

import java.util.Observer;

import tictactoe.rmi.RemoteObserver;

/**
 * Interface for TicTacToe and Gomoku.
 * @author $Author: oscar $
 * @version $Id: BoardGame.java 16633 2008-03-01 22:47:44Z oscar $
 */
public interface BoardGame {
	/**
	 * Number of columns in this game.
	 */
	public int getCols();
	/**
	 * Number of rows in this game.
	 */
	public int getRows();
	/**
	 * Join this game to play as the returned Player.
	 * Must be called twice.
	 */
	public Player join();
	/**
	 * Two Players have joined.
	 */
	public boolean ready();
	/**
	 * Returns which Player must play now.
	 */
	public Player currentPlayer();
	/**
	 * Return Player #n (0 or 1), else Player "nobody".
	 **/
	public Player player(int n);
	/**
	 * Player p attempts to play at position (col,row).
	 * @throws InvalidMoveException 
	 */
	public void move(int col, int row, Player p) throws InvalidMoveException;
	/**
	 * Returns the winner of the game.
	 */
	public Player winner();
	/**
	 * Returns the Player who has played at the given position.
	 */
	public Player get(int col, int row);
	/**
	 * The game is not over.
	 * (There is no winner and there are still empty squares.)
	 */
	public boolean notOver();
	/**
	 * The number of squares left unplayed.
	 */
	public int squaresLeft();
	/**
	 * The given position is valid.
	 */
	public boolean inRange(int col, int row);
	/**
	 * Add an Observer to be notified of updates.
	 */
	public void addObserver(Observer o);
	/**
	 * Used by GameProxy to connect RemoteObservers to a game.
	 **/
	public void addObserver(RemoteObserver remote);
	/**
	 * Restart the game, initializing the state.
	 */
	public void restart();
	/**
	 * Return the name of the game.
	 */
	public String name();
}

