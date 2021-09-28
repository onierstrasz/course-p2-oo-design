package generics;

import java.io.*;
import java.util.*;

/**
 * A simple application of the Java Collections framework
 * to "unjumble" words (as in "the Jumble Puzzle").
 * For example, "bjlemu" unjumbled is "jumble".<P>
 * This program exploits the idea that every legitimate word
 * can be assigned a unique key consisting of its letters
 * in sort order.  E.g., the key to "jumble" is "bejlmu".
 * Using a standard word list, we build up a map from keys to
 * the set of words that have that unique key.  To unjumble
 * a word, we simple compute its key and look it up in the Map.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: Jumble.java 26577 2009-04-30 12:48:54Z oscar $
 **/
@SuppressWarnings("serial")
public class Jumble extends HashMap<String, List<String>> {
	private boolean verbose;
	private String wordFile;

	/**
	 * Load the jumble dictionary with a list of words,
	 * then start the input loop to prompt for words to unjumble.
	 * @param args a file of unjumbled words (such as /etc/words)
	 **/
	public static void main(String args[]) {
		if (args.length == 1) {
			startJumble(args[0]);
		} else {
			// System.err.println("Usage: java Jumble <wordfile>");
			startJumble("words");
		}
	}
	
	public static void startJumble(String wordFile) {
		Jumble wordMap = null;
		try {
			wordMap = new Jumble(wordFile);
			// wordMap.dump();
		} catch (IOException err) {
			System.err.println("Can't load dictionary " + wordFile);
			return;
		}
		
		System.out.println("The largest set of mutual anagrams is: "
			+ wordMap.maxAnagrams());
		
		wordMap.inputLoop();
	}

	/**
	 * Initializes the Map of keys and unjumbled words.
	 * @param wordFile the name of the dictionary to load
	 **/
	Jumble(String wordFile) throws IOException {
		this(wordFile, true);
	}

	/**
	 * Initializes the Map.
	 * @param wordFile the name of the dictionary to load
	 * @param verbose report progress as the dictionary is loaded
	 **/
	Jumble(String wordFile, boolean verbose) throws IOException {
		super();
		this.verbose = verbose;
		this.wordFile = wordFile;
		loadDictionary();
	}
		
	/**
	 * A simple textual UI that prompts the user for words to unjumble.
	 **/
	public void inputLoop() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter a word to unjumble: ");
			String word;
			while ((word = in.readLine()) != null) {
				if (word.length() == 0) {
					System.out.print("Quit? (y/n): ");
					word = in.readLine();
					if ((word.length() == 1) && (word.charAt(0) == 'y')) {
						System.out.println("bye!");
						return;
					}
				} else {
					List<String> wordList = this.get(sortKey(word));
					if (wordList == null) {
						System.out.println("Can't unjumble " + word);
					} else {
						System.out.println(word + " unjumbles to: " + wordList);
					}
				}
				System.out.print("next word: ");
			}
		} catch (IOException err) {
			System.err.println("Can't get user input");
		}
	}

	/**
	 * Read in each word from the dictionary, and store the
	 * word together with its key in the Jumble Map.
	 **/
	private void loadDictionary() throws IOException {
		msg("Loading dictionary ");
 		BufferedReader in = new BufferedReader(new FileReader(wordFile));
		String word = in.readLine();
		while (word != null) {
			this.addPair(sortKey(word), word);
			word = in.readLine();
			if ((this.size() % 1000) == 0) msg(".");
		}
		msg(" ready!\n");
	}
	
	/**
	 * Add a (key, value) pair to the Jumble Map.
	 * Since each key may represent multiple words
	 * (e.g, asleep, elapse, please)
	 * we store a List of words for each key.
	 * @param key an alaphabetically sorted sequences of chars (e.g., aeelps)
	 * @param word a word containing all the characters of the key (e.g., please)
	 **/
	private void addPair(String key, String word) {
		List<String> wordList = this.get(key);
		if (wordList == null) {
			wordList = new ArrayList<String>();
		}
		wordList.add(word);
		this.put(key, wordList);
	}
	
	/**
	 * Optionary print a message.
	 **/
	private void msg(String s) {
		if (verbose) {
			System.out.print(s);
		}
	}

	/**
	 * Searches for the largest set of mutual anagrams.
	 **/
	public List<String> maxAnagrams() {
		int max = 0;
		List<String> anagrams = null;
		for (String key : this.keySet()) {
			List<String> words = this.get(key);
			if (words.size() > max) {
				anagrams = words;
				max = words.size();
			}
		}
		return anagrams;
	}

	/**
	 * Dump the contents of the dictionary into wordfile.dump.
	 * An illustration of iterators.
	 **/
	public void dump() throws IOException {
		msg("Dumping dictionary ");
		int i = 0;
		Writer out = new FileWriter(wordFile + ".dump");
		for (String key : this.keySet()) {
			out.write(key);
			for (String word : this.get(key)) {
				out.write(" " + word);
			}
			out.write("\n");
			if ((i++ % 1000) == 0) msg(".");
		}
		out.flush();
		out.close();
		msg(" done!\n");
	}

	/**
	 * Produce a key for a given word.
	 * E.g., sortKey("please") = "aeelps"
	 * @param word a word to sort
	 **/
	public static String sortKey(String word) {
		char [] letters = word.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}
