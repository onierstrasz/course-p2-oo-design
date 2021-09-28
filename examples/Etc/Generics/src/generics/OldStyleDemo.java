package generics;

import java.awt.Color;
import java.util.*;

/**
 * Demonstration of old style collections without generics.
 * 
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: OldStyleDemo.java 26578 2009-04-30 12:54:46Z oscar $
 */
public class OldStyleDemo {

	public static void main(String[] args) {
		new OldStyleDemo().oldStyle();
	}

	@SuppressWarnings("unchecked")
	List stones = new LinkedList();
	
	@SuppressWarnings("unchecked")
	void oldStyle() {
		
		// stones.add("ceci n'est pas un stone"); // run-time error on get()
		
		stones.add(new Stone(Color.RED));
		stones.add(new Stone(Color.GREEN));
		stones.add(new Stone(Color.RED));

		@SuppressWarnings("unused")
		Stone first = (Stone) stones.get(0); // annoying cast
		
		System.out.println("There are " + countStones(Color.RED) + " red stones.");
		
		printCollection(stones);
	}
	
	@SuppressWarnings("unchecked")
	int countStones(Color color){
		int tally = 0;
		Iterator it = stones.iterator();
		while (it.hasNext()) {
			Stone stone = (Stone) it.next(); // annoying cast
			if (stone.getColor() == color) {
				tally++;
			}
		}
		return tally;
	}
	
	@SuppressWarnings("unchecked")
	void printCollection(Collection c) {
		Iterator it = c.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
