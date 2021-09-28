package tictactoe;

/**
 * Minimal interface for Player classes that get moves from user
 * and forward them to the game.
 * @author $Author: oscar $
 * @version $Id: Player.java 16615 2008-03-01 17:08:43Z oscar $
 */
public interface Player {
	/**
	 * @return the char representation of this Player
	 * @see AbstractBoardGame#toString
	 */
	public char mark();
	/**
	 * @return whether this Player represents "nobody".
	 * (By convention, a Player with a blank as its mark.)
	 */
	public boolean isNobody();
	/**
	 * Let this player join a particular game.
	 * Set by a BoardGame when it is instantiated with Players.
	 */
	public void setGame(BoardGame game);
}

