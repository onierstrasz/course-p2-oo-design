package tictactoe;

/**
 * Bundles together information about a change of state
 * in a BoardGame.  Sent by AbstractBoardGame to its Observers.
 *
 * @author $Author: oscar $
 * @version $Id: Move.java 16522 2008-02-28 14:30:42Z oscar $
 */
public class Move {
	public final int col;
	public final int row;
	public final Player player;
	public Move(int col, int row, Player player) {
		this.col = col;
		this.row = row;
		this.player = player;
	}
	public String toString() {
		return "Move(" + col + "," + row + "," + player + ")";
	}
}