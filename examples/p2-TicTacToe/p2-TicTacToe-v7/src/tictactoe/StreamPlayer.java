package tictactoe;

import java.io.*;

/**
 * Manage interaction with user.  This implementation of Player
 * gets its moved from a BufferedReader.  This can either be
 * a wrapper around the standard input stream, or a wrapper
 * around a string representing a test case.
 *
 * @author $Author: oscar $
 * @version $Id: StreamPlayer.java 16628 2008-03-01 22:30:09Z oscar $
 */
public class StreamPlayer extends InactivePlayer {
	protected final BufferedReader in;

	/**
	 * Constructor to specify an alternative source of moves
	 * (e.g., a test case StringReader).
	 */
	public StreamPlayer(char mark, BufferedReader initIn) {
		super(mark);
		in = initIn;
	}
	
	/**
	 * The normal constructor to use.  Just define the mark.
	 * The Player will get its input from the standard input stream.
	 */
	public StreamPlayer(char mark) {
		this(mark, new BufferedReader(new InputStreamReader(System.in)));
	}

	/**
	 * Special constructor to make a Player that plays a fixed
	 * set of moves from a String.  Used to define test cases.
	 */
	public StreamPlayer(char mark, String moves) {
		this(mark, new BufferedReader(new StringReader(moves)));
	}

	/**
	 * The Player makes a move by reading a line of text from
	 * the input stream, and interpreting it using chess notation,
	 * i.e., column is 'a' through 'c' and row is
	 * '1' through '3'.  The converted position is used to
	 * call BoardGame.move().
	 * @throws InvalidMoveException 
	 *
	 * @see GameDriver#playGame
	 */
	public void move() throws IOException, InvalidMoveException
	{
		String line = in.readLine();
		if (line == null) {
			throw new IOException("end of input");
		}
		game.move(getCol(line), getRow(line), this);
	}

	/**
	 * Interpret the first char of a string as a letter identifying
	 * a column of a BoardGame.
	 */
	protected int getCol(String coord) {
		assert coord != null;
		assert coord.length() > 0;
		char col = coord.charAt(0);
		assert (col>='a') && (col<='z');
		return col - 'a';
	}
	
	/**
	 * Interpret the second and following characters of a string
	 * as a number identifying a row of a BoardGame.
	 */
	protected int getRow(String coord) {
		assert coord != null;
		assert coord.length() > 1;
		try {
			int row = Integer.parseInt(coord.substring(1));
			return row - 1;
		} catch (NumberFormatException err) {
			throw new AssertionError(err.getMessage());
		}
	}
}
