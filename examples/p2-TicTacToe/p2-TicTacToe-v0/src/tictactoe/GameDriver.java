package tictactoe;

/**
 * Driver for TicTacToe.
 * @author $Author: oscar $
 * @version $Id: GameDriver.java 16447 2008-02-27 13:26:54Z oscar $
 */
public class GameDriver {
	static public void main(String args[]) {
		TicTacToe game = new TicTacToe();
		do {
			System.out.print(game);
		} while(game.notOver());
	}
}
