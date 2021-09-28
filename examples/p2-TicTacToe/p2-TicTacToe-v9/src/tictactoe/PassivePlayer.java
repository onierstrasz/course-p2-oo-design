package tictactoe;

/**
 * A PassivePlayer attempts to make a move when its
 * move() method is called by some Driver.
 * Used by the GameServer
 * @author $Author: oscar $
 * @version $Id: PassivePlayer.java 16633 2008-03-01 22:47:44Z oscar $
 **/
public class PassivePlayer extends InactivePlayer {

	public PassivePlayer(char mark) {
		super(mark);
	}
	
	/**
	 * Attempt to make the given move in the associated game.
	 * @throws InvalidMoveException 
	 **/
	public void move(int col, int row) throws InvalidMoveException {
		game.move(col, row, this);
	}
}
