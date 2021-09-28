package p2.stack;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * A utility class to help us run benchmarks.
 *
 * @author akuhn@iam.unibe.ch
 * @version 1.0 2007-04-20
 */
public class LinkStackTest {
    
	protected StackInterface stack;
	private int size;
	
	@Before public void setUp() {
		stack = new LinkStack();
	}
	
	@Test public void empty() {
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
	}
	
	@Test(expected=AssertionError.class)
	public void emptyTopFails() {
		stack.top();
	}

	@Test(expected=AssertionError.class)
	public void emptyRemoveFails() {
		stack.pop();
	}

	@Test public void nullPush() {
		stack.push(null);
		assertFalse(stack.isEmpty());
		assertEquals(null, stack.top());
		assertEquals(1, size = stack.size());
		stack.pop();
		assertEquals(size -1, stack.size());		
	}
	@Test public void oneElement() {
		stack.push("a");
		assertFalse(stack.isEmpty());
		assertEquals(1, size = stack.size());
		stack.pop();
		assertEquals(size -1, stack.size());		
	}
	
	@Test public void twoElement() {
		stack.push("a");
		stack.push("b");
		assertFalse(stack.isEmpty());
		assertEquals(2, size = stack.size());
		stack.pop();
		assertEquals(size -1, stack.size());			
	}

	@Test public void sevenElement() {
		size = 7;
		for (int n = 0; n < 7; n++) stack.push(n);
		assertFalse(stack.isEmpty());
		assertEquals(size, stack.size());
		stack.pop();
		stack.pop();
		stack.pop();
		assertEquals(size -3, stack.size());			
	}
	
	@Test public void firstInLastOut() {
		stack.push("a");
		stack.push("b");
		stack.push("c");
		assertEquals("c", stack.top());
		stack.pop();
		assertEquals("b", stack.top());
		stack.pop();
		assertEquals("a", stack.top());
		stack.pop();
		assertTrue(stack.isEmpty());		
	}
	
}
