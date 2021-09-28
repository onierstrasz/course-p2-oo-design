package p2.stack;

import org.junit.Before;

/**
 * Override setUp to run the same generic tests with
 * a different Stack implementation.
 *
 * @author oscar@iam.unibe.ch
 * @version 1.0 2008-02-25
 */
public class ArrayStackTest extends LinkStackTest {

	@Before public void setUp() {
		// stack = new BrokenArrayStack();
		stack = new ArrayStack();
	}
	
}
