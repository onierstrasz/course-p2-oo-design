
package p2.genstack;

import java.util.Stack;

/**
 * This class is an adaptor that delegates StackInterface
 * messages to a java.util.Stack instance.
 * This version is broken because it does not check the contract.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: SimpleWrappedStack.java 17003 2008-03-13 10:20:41Z oscar $
 */
public class SimpleWrappedStack<E> implements StackInterface<E> {

	protected java.util.Stack<E> stack;
	
	public SimpleWrappedStack() {
		this(new Stack<E>());
	}
	
	public SimpleWrappedStack(Stack<E> stack) {
		this.stack = stack;
	}
	
	public void push(E item) {
		stack.push(item);
	}
	
	public E top() {
		return stack.peek();
	}
	
	public void pop() {
		stack.pop();
	}

	public boolean isEmpty() {
		return stack.empty();
	}

	public int size() {
		return stack.size();
	}
}
