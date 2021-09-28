package tictactoe;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Needed for silent testing. We will instantiate a
 * silent PrintStream by instantiating:
 * 		new PrintStream(new NullOutputStream())
 * in TicTacToeTest ...
 * @author $Author: oscar $
 * @version $Id: NullOutputStream.java 16615 2008-03-01 17:08:43Z oscar $
 */
public class NullOutputStream extends OutputStream {

	public NullOutputStream() {
		super();
	}

	/**
	 * Null implementation of inherited abstract method
	 */
	public void write(int b) throws IOException { }
}
