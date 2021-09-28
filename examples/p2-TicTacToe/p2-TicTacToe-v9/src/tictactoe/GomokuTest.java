package tictactoe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author $Author: oscar $
 * @version $Id: GomokuTest.java 16536 2008-02-28 17:40:24Z oscar $
 */
public class GomokuTest extends AbstractBoardGameTest {
	
	@Test public void testOKinput() {
		String input = "a2";
		assertEquals(((Gomoku) game).getCol(input), 0);
		assertEquals(((Gomoku) game).getRow(input), 1);
		input = "b4";
		assertEquals(((Gomoku) game).getCol(input), 1);
		assertEquals(((Gomoku) game).getRow(input), 3);
		input = "c10";
		assertEquals(((Gomoku) game).getCol(input), 2);
		assertEquals(((Gomoku) game).getRow(input), 9);
	}

	@Test public void testBadRow() {
		final String input = "aaa";
		assertEquals(((Gomoku) game).getCol(input), 0);
		assertFails(new Runnable() {
			public void run() {
				((Gomoku) game).getRow(input);
			}
		});
	}

	@Test public void testEmptyInput() {
		final String input = "";
		assertFails(new Runnable() {
			public void run() {
				((Gomoku) game).getCol(input);
			}
		});
	}

	@Test public void testBadCol() {
		final String input = "X10";
		assertFails(new Runnable() {
			public void run() {
				((Gomoku) game).getCol(input);
			}
		});
		assertEquals(((Gomoku) game).getRow(input), 9);
	}

	@Test public void testXWinsDiagonal() {
		checkGame("\naa\n" // nonsense input
						+ "f6\ng5\ne7\nd8\nc9\n",
					"b2\nh4\nc3\nd4\n",
					"X", ((Gomoku)game).squares() - 9);
	}
	
	protected BoardGame makeGame(Player X, Player O) {
		return new Gomoku(X, O);
	}
}
