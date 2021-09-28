package snakes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleGameTest {
	
	protected Player jack;
	protected Player jill;
	protected Game game;

	@BeforeEach
	public void setUp() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill };
		game = new Game(12, args);
		game.setSquareToLadder(2, 4);
		game.setSquareToLadder(7, 2);
		game.setSquareToSnake(11, -6);
	}

	public Game newGame() {
		this.setUp();
		return game;
	}

	@Test
	public void testNewGame() {
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
	}

	@Test
	public void testInitialStrings() {
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[2->6]", game.getSquare(2).toString());
		assertEquals("[5<-11]", game.getSquare(11).toString());
		assertEquals("[1<Jack><Jill>][2->6][3][4][5][6][7->9][8][9][10][5<-11][12]", game.toString());
	}

	@Test
	public void move1jack() {
		game.movePlayer(4);
		assertTrue(game.notOver());
		assertEquals(5, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		assertEquals("[1<Jill>]", game.firstSquare().toString());
		assertEquals("[5<Jack>]", game.getSquare(5).toString());
	}

	@Test
	public void move2jackBackwards() {
		game.movePlayer(4);
		jack.moveForward(7+11); // move to end and back to start
		assertEquals(1, jack.position());
		assertEquals("[1<Jill><Jack>]", game.firstSquare().toString());
	}

	@Test
	public void move2jillLadder() {
		game.movePlayer(4);
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(5, jack.position());
		assertEquals(6, jill.position());
		assertEquals(jack, game.currentPlayer());
	}

	@Test
	public void move3jackMeetsJill() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		assertFalse(game.getSquare(5).isOccupied());
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(6, jill.position());
		assertEquals(jill, game.currentPlayer());
	}

	@Test
	public void move4jillSnake() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(5, jill.position());
		assertEquals(jack, game.currentPlayer());
	}

	@Test
	public void move5jackLadder() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		game.movePlayer(5);
		game.movePlayer(6);
		assertTrue(game.notOver());
		assertEquals(9, jack.position());
		assertEquals(5, jill.position());
		assertEquals(jill, game.currentPlayer());
	}

	@Test
	public void move6jill() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		game.movePlayer(5);
		game.movePlayer(6);
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(9, jack.position());
		assertEquals(10, jill.position());
		assertEquals(jack, game.currentPlayer());
	}

	@Test
	public void move7jackBouncesBackToJill() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		game.movePlayer(5);
		game.movePlayer(6);
		game.movePlayer(5);
		game.movePlayer(5);
		assertTrue(game.notOver());
		assertEquals(1, jack.position());
		assertEquals(10, jill.position());
		assertEquals(jill, game.currentPlayer());
	}

	@Test
	public void move8jillWins() {
		game.movePlayer(4);
		game.movePlayer(1);
		game.movePlayer(1);
		game.movePlayer(5);
		game.movePlayer(6);
		game.movePlayer(5);
		game.movePlayer(5);
		game.movePlayer(2);
		assertTrue(game.isOver());
		assertEquals(1, jack.position());
		assertEquals(12, jill.position());
		assertEquals(jack, game.currentPlayer());
		assertEquals(jill, game.winner());
	}
}
