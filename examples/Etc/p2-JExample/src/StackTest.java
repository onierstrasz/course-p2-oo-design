
import java.util.EmptyStackException;
import java.util.Stack;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.runner.RunWith;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;

@RunWith(JExample.class)
public class StackTest {

	@Test
	public Stack<String> empty() {
		Stack<String> stack = new Stack<String>();
		assertTrue(stack.empty());
		return stack;
	}
	
	@Test(expected=EmptyStackException.class)
	@Given("#empty")
	public void emptyPopFails(Stack<String> stack) {
		stack.pop();
	}

	@Test
	@Given("#empty")
	public Stack<String> pushOnEmpty(Stack<String> stack) {
		stack.push("foo");
		assertFalse(stack.empty());
		assertEquals(stack.size(), 1);
		return stack;
	}

	@Test
	@Given("#pushOnEmpty")
	public Stack<String> pushPop(Stack<String> stack) {
		stack.pop();
		assertTrue(stack.empty());
		return stack;
	}
	
	@Test
	@Given("#pushPop; #empty")
	public void equality(Stack<String> used, Stack<String> fresh) {
		assertEquals(used, fresh);
	}

}
