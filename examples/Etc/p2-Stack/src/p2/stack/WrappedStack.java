
package p2.stack;

import java.util.Stack;

/**
 * This class is an adaptor that delegates StackInterface
 * messages to a java.util.Stack instance.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version 1.2 2007-04-23 -- removed superclass 
 * version 1.1 2005-02-15 -- adapted to Java 1.4
 * version 1.0 1998-11-25
 */
public class WrappedStack implements StackInterface {

	private Stack<Object> stack;
	
	public WrappedStack() {
		this(new Stack<Object>());
	}
	
	public WrappedStack(Stack<Object> stack) {
		this.stack = stack;
	}
	
	public void push(Object item) {
		stack.push(item);
		assert this.top() == item;
		assert invariant();
	}
	
	public Object top() {
		assert !this.isEmpty();
		return stack.peek();
	}
	
	public void pop() {
		assert !this.isEmpty();
		stack.pop();
		assert invariant();
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
