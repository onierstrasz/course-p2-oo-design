package p2.stack;

/**
 * Canonical stack interface -- without an isFull() method.
 * This is <i>not</i> compatible with java.util.Stack.
 * @see LinkStack
 * @see ArrayStack
 * @see WrappedStack
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version 1.0 1998-11-25
 */
public interface StackInterface {
	public boolean isEmpty();
	public int size();
	public void push(Object item);
	public Object top();
	public void pop();
}
