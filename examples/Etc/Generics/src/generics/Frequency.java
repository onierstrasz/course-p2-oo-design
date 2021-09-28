package generics;

import java.util.*;

/**
 * Prints a frequency table of the words on the command line.
 * Demo of autoboxing.
 * 
 * (Rescued from old examples.)
 */
public class Frequency {
	public static void main(String[] args) {
		Map<String, Integer> m = new TreeMap<String, Integer>();
		for (String word : args) {
			Integer freq = m.get(word); // autoboxing
			m.put(word, (freq == null ? 1 : freq + 1)); // auto unboxing to perform addition
		}
		System.out.println(m);
	}
}



