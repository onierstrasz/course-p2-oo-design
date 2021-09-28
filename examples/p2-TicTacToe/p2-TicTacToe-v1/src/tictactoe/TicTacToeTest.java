package tictactoe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for TicTacToe.
 * @author $Author: oscar $
 * @version $Id: TicTacToeTest.java 16480 2008-02-27 15:02:28Z oscar $
 */
public class TicTacToeTest {
	protected TicTacToe game;

	@BeforeEach public void setUp() {
		game = new TicTacToe();
	}
	
	/**
	 * Test the getters and setters.
	 */
	@Test public void testState() {
		assertEquals(game.get('a','1'), ' ');
		assertEquals(game.get('c','3'), ' ');
		game.set('c','3','X');
		assertEquals(game.get('c','3'), 'X');
		game.set('c','3',' ');
		assertEquals(game.get('c','3'), ' ');
		assertFalse(game.inRange('d','4'));
	}

}
