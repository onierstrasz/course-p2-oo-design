package tictactoe;

/**
 * Bundles together information about a change of state
 * in a BoardGame.  Sent by AbstractBoardGame to its Observers.
 *
 * @author $Author: oscar $
 * @version $Id: Move.java 16537 2008-02-28 17:44:24Z oscar $
 */
@SuppressWarnings("serial")
public class Move implements java.io.Serializable {
	public final int col;
	public final int row;
	public final char mark;
	public Move(int col, int row, char mark) {
		this.col = col;
		this.row = row;
		this.mark = mark;
	}
	public String toString() {
		return "Move(" + col + "," + row + "," + mark + ")";
	}
}