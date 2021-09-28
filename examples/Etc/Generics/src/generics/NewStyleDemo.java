package generics;

import java.awt.Color;
import java.util.*;

/**
 * Demonstration of new-style collections with generics.
 * Compare with OldStyleDemo.java
 * 
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: NewStyleDemo.java 26578 2009-04-30 12:54:46Z oscar $
 */
public class NewStyleDemo {

	public static void main(String[] args) {
		new NewStyleDemo().newStyle();
	}

	List<Stone> stones = new LinkedList<Stone>();
	
	void newStyle() {
		
		// stones.add("ceci n'est pas un stone"); // compile-time error!

		stones.add(new Stone(Color.RED));
		stones.add(new Stone(Color.GREEN));
		stones.add(new Stone(Color.GREEN));

		@SuppressWarnings("unused")
		Stone first = stones.get(0); // no cast
		
		System.out.println("There are " + countStones(Color.GREEN) + " green stones.");

		// naivePrintCollection(stones); // won't compile
		printCollection(stones);
		
		// pitfalls();
	}
	
	int countStones(Color color){
		int tally = 0;
		for (Stone stone : stones) { // no temporary, no cast
			if (stone.getColor() == color) {
				tally++;
			}
		}
		return tally;
	}

	// No good -- a List of Stones does not conform to a Collection of Objects
	void naivePrintCollection(Collection<Object> c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}

	// Need wildcard match -- a List of Stones will match a Collection of "any"
	void printCollection(Collection<?> c) {
		for (Object o : c) {
			System.out.println(o);
		}
	}
	
	@SuppressWarnings("unchecked")
	void pitfalls() {
		@SuppressWarnings("unused")
		String myString;
		@SuppressWarnings("unused")
		Object myObject;
		List<?> c = new ArrayList<String>();
		
		// c.add("hello world"); // compile error
		// c.add(new Object()); // compile error
		((List<String>) c).add("hello world");
		((List<Object>) c).add(new Object()); // no compile error!
		
		// String myString = c.get(0); // compile error
		myString = (String) c.get(0);
		myObject = c.get(0);
		myString = (String) c.get(1); // run-time error!
	}

	void compileCheck() {
		List<String> ls = new ArrayList<String>();
		// List<Object> lo = ls; // won't compile
		@SuppressWarnings("unused")
		List<?> lo = ls; // this is ok, but evil
	}

}
