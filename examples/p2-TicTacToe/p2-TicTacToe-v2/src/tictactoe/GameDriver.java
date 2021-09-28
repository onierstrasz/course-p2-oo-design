package tictactoe;
import java.io.IOException;

/**
 * Driver for TicTacToe.
 * @author $Author: oscar $
 * @version $Id: GameDriver.java 16457 2008-02-27 13:54:28Z oscar $
 */

public class GameDriver {
	public static void main(String args[]) {
		Player X = new Player('X');
		Player O = new Player('O');
		TicTacToe game = new TicTacToe(X, O);
		playGame(game);
	}

	public static void playGame(TicTacToe game) {
		try {
			do {
				System.out.print(game);
				game.update();
			} while(game.notOver());
			System.out.print(game);
			System.out.println("game over!");
		} catch (IOException err) {
			System.out.println("game terminated!");
		}
	}
}