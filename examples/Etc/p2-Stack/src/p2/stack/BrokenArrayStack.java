package p2.stack;

/**
 * This is the broken version of ArrayStack that
 * only contains preconditions and contains a subtle
 * bug in the pop() method.
 *
 * @author Oscar.Nierstrasz@acm.org
 */
public class BrokenArrayStack implements StackInterface {
	private Object store [];
	private int capacity;
	private int size;
	
	public BrokenArrayStack() {
		store = null; // default value
		capacity = 0;
		size = 0;
		assert invariant();
	}
	
	public boolean isEmpty() { return size == 0; }
	public int size() { return size; }

	public void push(Object item) {
		if (size == capacity) { grow(); }
		// NB: top index is the *old* value of size
		store[++size] = item; // subtle bug
	}
	
	public Object top() {
		assert !this.isEmpty();
		return store[size-1];
	}
	
	public void pop() {
		assert !this.isEmpty();
		size--;
		// should we have a shrink() method?
	}
	
	/**
	 * This is where the magic occurs.  If the stack's
	 * store runs out of space, we allocate a new one
	 * and copy the old values.  The old array will eventually
	 * be garbage-collected.  Note that initially capacity == 0,
	 * so we add one before doubling it!
	 * <p>
	 * There should probably be a shrink() method to free up
	 * space when the size becomes significantly smaller than
	 * the capacity!
	 */
	private void grow() {
		int oldCapacity = capacity;
		Object oldStore[] = store;
		
		capacity = 2 * (1+oldCapacity);
		store = new Object[capacity];
		
		for (int i=0; i<size; i++) {
			store[i] = oldStore[i];
		}
	}
	
	private boolean invariant() {
		return (size >= 0) && (size <= capacity);
	}
}