package tictactoe;

/**
 * Manage interaction with user.
 * This class implements "nobody" -- a Player that has an identity,
 * but does not make moves.  Serves as a superclass for other implementations.
 * @author $Author: oscar $
 * @version $Id: InactivePlayer.java 16608 2008-03-01 15:38:08Z oscar $
 */
public class InactivePlayer implements Player {
	protected final char mark;
	protected BoardGame game;

	/**
	 * The normal contructor to use.
	 * @param myMark the character representing this player ('X' or 'O')
	 */
	public InactivePlayer(char myMark) {
		mark = myMark;
	}
	
	/**
	 * Special constructor for the Player representing nobody.
	 * (Used by TicTacToe as a placeholder when there is no
	 * winner yet.)
	 * @see AbstractBoardGame
	 */
	public InactivePlayer() {
		this(' ');
	}

	/**
	 * @return the char representation of this Player
	 * @see AbstractBoardGame#toString
	 */
	public char mark() { return mark; }
	
	/**
	 * @return the String representation of this Player
	 * @see GameDriver#playGame
	 */
	public String toString() {
		if (this.isNobody())
			return "nobody";
		else
			return new Character(this.mark()).toString();
	}
	
	/**
	 * @return whether this Player represents "nobody".
	 * (By convention, a Player with a blank as its mark.)
	 */
	public boolean isNobody() {
		return this.mark() == ' ';
	}

	/**
	 * Let this player join a particular game.
	 * Set by a BoardGame when it is instantiated with Players.
	 */
	public void setGame(BoardGame aGame) {
		game = aGame;
	}
}
