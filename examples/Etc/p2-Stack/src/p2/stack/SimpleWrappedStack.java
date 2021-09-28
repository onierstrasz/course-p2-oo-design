
package p2.stack;

import java.util.Stack;

/**
 * This class is an adaptor that delegates StackInterface
 * messages to a java.util.Stack instance.
 * This version is broken because it does not check the contract.
 *
 * @author Oscar.Nierstrasz@acm.org
 * version 1.1 2005-02-15 -- adapted to Java 1.4
 * version 1.0 1998-11-25
 */
public class SimpleWrappedStack implements StackInterface {

	private Stack<Object> stack;
	
	public SimpleWrappedStack() {
		this(new Stack<Object>());
		assert invariant();
	}
	
	public SimpleWrappedStack(Stack<Object> stack) {
		this.stack = stack;
		assert invariant();
	}
	
	public void push(Object item) {
		stack.push(item);
	}
	
	public Object top() {
		return stack.peek();
	}
	
	public void pop() {
		stack.pop();
	}
	
	private boolean invariant() {
		return this.size() >= 0;
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public int size() {
		return stack.size();
	}
}
