package tictactoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Driver for TicTacToe.
 * @author $Author: oscar $
 * @version $Id: GameDriver.java 16639 2008-03-01 23:26:29Z oscar $
 */

public class GameDriver {
	
	/**
	 * Start an ascii game.
	 */
	public static void main(String args[]) {
		Player X = new StreamPlayer('X');
		Player O = new StreamPlayer('O');
		
		System.out.print("Hi!  Would you like to play TicTacToe (t) or Gomoku (g)?: ");
		
		switch(readChar()) {
		case 't':
			playGame(new TicTacToe(X, O));
			break;
		case 'g':
			playGame(new Gomoku(X, O));
			break;
		default:
			System.out.println("I guess you don't want to play!");
		}
		System.out.println("bye!");
	}
	
	/**
	 * Read a char from the input stream.
	 */
	public static char readChar() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line = in.readLine();
			if ((line == null) || (line.length() == 0))
				return '\n';
			else
				return line.charAt(0);
		} catch (IOException err) {
			return '\n';
		}
	}
	
	/**
	 * Play the game with the standard output streams.
	 */
	public static void playGame(BoardGame game) {
		playGame(game, System.out, System.err);
	}
	
	/**
	 * Play the game with a given pair of output streams.
	 * Needed for testing in silent mode.
	 */
	public static void playGame(BoardGame game, PrintStream out, PrintStream err)
	{
		try {
			game.join(); // X joins
			game.join(); // O joins
			do {
				out.println();
				out.println(game);
				out.print("Player "
						+ game.currentPlayer().mark() + " moves: ");
				try {
					((StreamPlayer) game.currentPlayer()).move();
				} catch(AssertionError e) {
					err.println(e.getMessage());
				} catch (InvalidMoveException e) {
					err.println(e.getMessage());
				}
			} while(game.notOver());
			out.println();
			out.print(game);
			out.println("game over -- " + game.winner() + " wins!");
		} catch (IOException e) {
			err.println("game terminated!");
		}
	}
}