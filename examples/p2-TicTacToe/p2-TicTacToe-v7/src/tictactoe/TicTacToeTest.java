package tictactoe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for TicTacToe.
 * @author $Author: oscar $
 * @version $Id: TicTacToeTest.java 16498 2008-02-27 16:07:09Z oscar $
 */
public class TicTacToeTest extends AbstractBoardGameTest {
	
	/**
	 * Test the getters and setters.
	 */
	@Test public void testState() {
		assertEquals(game.get(0,0).mark(), ' ');
		assertEquals(game.get(2,2).mark(), ' ');
		((TicTacToe) game).set(2,2,X);
		assertEquals(game.get(2,2),X);
		((TicTacToe) game).set(2,2,O);
		assertEquals(game.get(2,2), O);
		assertFalse(game.inRange(3,3));
	}
	
	@Test public void testXWinDiagonal() {
		checkGame("a1\nb2\nc3\n", "b1\nc1\n", "X", 4);
	}

	@Test public void testNoWinner() {
		checkGame("b2\na1\nb3\na2\nc1\n",
				"a3\nc3\nb1\nc2\n", "nobody",0);
	}
	
	@Test public void testOWinReverseDiagonal() {
		checkGame("a1\nb1\n"
				+ "a1\nb1\nb2\nc1\nd0\nxxx\n\n" // invalid moves
				+ "a2\n",
				"b2\nc1\na3\n", "O", 3);
	}
	
	@Test public void testXWinCentreColumn() {
		checkGame("b2\nb1\nb3\n",
				"a1\n"
				+ "b1\nA0\n" // invalid moves
				+ "a3\n", "X", 4);
	}
	
	@Test public void testOWinTopRow() {
		checkGame("b2\na1\nc1\n",
				"a3\nc3\nb3\n", "O", 3);
	}
	
	protected BoardGame makeGame(Player X, Player O) {
		return new TicTacToe(X, O);
	}
}
