package tictactoe;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author $Author: oscar $
 * @version $Id: AbstractBoardGameTest.java 16485 2008-02-27 15:09:10Z oscar $
 */
public abstract class AbstractBoardGameTest {
	protected BoardGame game;

	@BeforeEach public void setUp() throws Exception {
		game = this.makeGame(new Player('X'), new Player('O'));
	}
		
	/**
	 * Run a game with simulated input for X and Y, and check that
	 * the winner is as expected.
	 * Game results are sent to a NullOutputStream.
	 */
	public void checkGame(String Xmoves, String Omoves, String winner, int squaresLeft) {
		Player X = new Player('X', Xmoves);
		Player O = new Player('O', Omoves);
		game = makeGame(X,O);
		GameDriver.playGame(game, new PrintStream(new NullOutputStream()));
		// System.out.println(game.winner().name() + " = " + winner);
		assertEquals(winner, game.winner().name());
		// System.out.println(game.squaresLeft() + " = " + squaresLeft + " squares left");
		assertEquals(squaresLeft, game.squaresLeft());
	}
	
	/**
	 * Factory method so subclasses can create other games.
	 */
	abstract protected BoardGame makeGame(Player X, Player O);

	/**
	 * Run a command that should fail.
	 */
	public void assertFails(Runnable command) {
		boolean caught = false;
		try {
			command.run();
		} catch(AssertionError err) {
			caught = true;
		}
		assertTrue(caught);
	}
}
