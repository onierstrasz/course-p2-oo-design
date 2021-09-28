package p2.genstack;

import org.junit.jupiter.api.BeforeEach;

/**
 * Override setUp to run the same generic tests with
 * a different Stack implementation.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: WrappedStackTest.java 17003 2008-03-13 10:20:41Z oscar $
 */
public class WrappedStackTest extends LinkStackTest {

	@BeforeEach
    public void setUp() {
		// stack = new SimpleWrappedStack<String>();
		stack = new WrappedStack<String>();
	}
	
}
