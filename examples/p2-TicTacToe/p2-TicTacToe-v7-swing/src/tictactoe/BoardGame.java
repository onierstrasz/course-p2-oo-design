package tictactoe;

import java.util.Observer;

/**
 * Interface for TicTacToe and Gomoku.
 * @author $Author: oscar $
 * @version $Id: BoardGame.java 16629 2008-03-01 22:34:04Z oscar $
 */
public interface BoardGame {
	public int getCols();
	public int getRows();
	public void move(int col, int row, Player p) throws InvalidMoveException;
	public Player currentPlayer();
	public Player winner();
	public Player get(int col, int row);
	public boolean notOver();
	public int squaresLeft();
	public boolean inRange(int col, int row);
	public void addObserver(Observer o);
}

