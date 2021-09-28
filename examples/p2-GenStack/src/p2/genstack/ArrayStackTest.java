package p2.genstack;

import org.junit.jupiter.api.BeforeEach;

/**
 * Override setUp to run the same generic tests with
 * a different Stack implementation.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: ArrayStackTest.java 17003 2008-03-13 10:20:41Z oscar $
 */
public class ArrayStackTest extends LinkStackTest {

	@BeforeEach
    public void setUp() {
		stack = new ArrayStack<String>();
	}
	
}
