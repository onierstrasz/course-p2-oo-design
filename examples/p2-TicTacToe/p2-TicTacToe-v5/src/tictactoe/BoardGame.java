package tictactoe;
import java.io.*;

/**
 * Interface for TicTacToe and Gomoku.
 * @author $Author: oscar $
 * @version $Id: BoardGame.java 16466 2008-02-27 14:10:55Z oscar $
 */
public interface BoardGame {
	public void update() throws IOException;
	public void move(String coord, char mark);
	public Player currentPlayer();
	public Player winner();
	public boolean notOver();
	public int squaresLeft();
}

