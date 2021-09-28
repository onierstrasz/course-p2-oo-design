package tictactoe;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	InvalidMoveException(String err) {
		super(err);
	}
}
