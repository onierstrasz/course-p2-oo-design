package p2.stack;

/**
 * This Stack implementation uses arrays of fixed length.
 * Whenever the array runs out of space, a new one is
 * allocated and the old data is copied.<p>
 * <i>NB:</i> ArrayStack is <i>not</i> thread safe!
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version 1.1 2005-02-15 -- adapted to Java 1.4
 * @version 1.0 1998-11-25
 */
public class ArrayStack implements StackInterface {
	private Object store [];
	private int capacity;
	private int size;
	
	public ArrayStack() {
		store = null; // default value
		capacity = 0; // available slots
		size = 0; // used slots
	}
	
	public boolean isEmpty() { return size == 0; }
	public int size() { return size; }

	public void push(Object item) {
		if (size == capacity) { grow(); }
		// NB: top index is the *old* value of size
		store[size++] = item;
		// store[++size] = item;
		assert this.top() == item;
		assert invariant();
	}
	
	public Object top() {
		assert !this.isEmpty();
		return store[size-1];
	}
	
	public void pop() {
		assert !this.isEmpty();
		size--;
		// should we have a shrink() method?
		assert invariant();
	}
	
	/**
	 * This is where the magic occurs.  If the stack's
	 * store runs out of space, we allocate a new one
	 * and copy the old values.  The old array will eventually
	 * be garbage-collected.  Note that initially capacity == 0,
	 * so we add one before doubling it!
	 * <p>
	 * There should probably be a shrink() method to free up
	 * space when the size becomes siginicantly smaller than
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