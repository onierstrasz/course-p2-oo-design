package p2.stack;

import static org.junit.Assert.*;
import org.junit.*;

public class ParenMatchTest {

	protected StackInterface stack;
	
	@Before public void setUp() {
		stack = new LinkStack();
	}
	
	@Test public void empty() {
		assertTrue(new ParenMatch("", stack).parenMatch());
	}

	@Test public void simple() {
		assertTrue(new ParenMatch("hello()", stack).parenMatch());
	}

	@Test public void complex() {
		assertTrue(new ParenMatch("foo(bar{}){ baz[]() }", stack).parenMatch());
	}

	@Test public void notmatched() {
		assertFalse(new ParenMatch("[[[}}}", stack).parenMatch());
	}
	
}
