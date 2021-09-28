package tictactoe;
import java.io.*;

/**
 * Manage interaction with user.
 * @author $Author: oscar $
 * @version $Id: Player.java 16599 2008-03-01 14:57:54Z oscar $
 */
public class Player {
	protected final char mark;
	protected final BufferedReader in;
	
	/**
	 * Constructor to specify an alternative source of moves
	 * (e.g., a test case StringReader).
	 */
	public Player(char initMark, BufferedReader initIn) {
		mark = initMark;
		in = initIn;
	}
	
	/**
	 * The normal contructor to use:
	 */
	public Player(char initMark) {
		this(initMark, new BufferedReader(new InputStreamReader(System.in)));
	}
	
	/**
	 * Special constructor to make a Player that plays a fixed
	 * set of moves from a String.  Used to define test cases.
	 */
	public Player(char initMark, String moves) {
		this(initMark, new BufferedReader(new StringReader(moves)));
	}
	
	/**
	 * Special constructor for the Player representing nobody.
	 * (Used by TicTacToe as a placeholder when there is no
	 * winner yet.)
	 */
	public Player() {
		this(' ');
	}
	
	/**
	 * @return the char representing the Player
	 */
	public char mark() {
		return mark;
	}
	
	/**
	 * @return String representation of Player
	 */
	public String name() {
		if (this.isNobody())
			return "nobody";
		else
			return new Character(this.mark()).toString();
	}
	
	/**
	 * By convention, a Player without a mark is nobody!
	 */
	public boolean isNobody() {
		return this.mark() == ' ';
	}
	
	/**
	 * Ask the user (or script) where to move
	 * and attempt to perform that move.
	 */
	public void move(BoardGame game) throws IOException
	{
		String line;
		line = in.readLine();
		if (line == null) {
			throw new IOException("end of input");
		}
		game.move(line, this.mark());
	}
}
