package p2.stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Demonstrates a textbook algorithm using a stack:
 * walk through a string containing various kinds of
 * parentheses (like "if (a) { b[c++]; } else { e(f); }")
 * and report whether the parentheses are balanced.
 *
 * @author Oscar.Nierstrasz@acm.org
 * version 1.3 2008-02-29 -- introduced autoboxing
 * version 1.2 2005-02-13 -- moved main method here from TestStack; converted to Java 1.4 assertions
 * version 1.1 1999-04-10 -- added declarative version of parenMatch()
 * version 1.0 1998-11-25
 */
public class ParenMatch {
	
	private String line;
	private StackInterface stack;
	
	/**
	 * The expression (line) to test and the stack to use
	 * are parameters so we can try different stack implementations.
	 * There should be a default constructor ...
	 */
	public ParenMatch(String aLine, StackInterface aStack) {
		line = aLine;
		stack = aStack;
	}
	
	/**
	 * This classic algorithm pushes a value on the stack
	 * every time a left parenthesis is encountered, and
	 * pops a value when a right parenthesis is encountered.
	 * If and only if all the parentheses match,
	 * and if the stack is empty at the end,
	 * then everything is ok.
	 * Equivalent to uglyParenMatch(), but rewritten in declarative style
	 */
	public boolean parenMatch() {
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			if (isLeftParen(c)) {
				// expect matching right paren later
				stack.push(matchingRightParen(c)); // Autoboxed to Character
			} else {
				if (isRightParen(c)) {
					// empty stack => missing left paren
					if (stack.isEmpty()) { return false; }
					if (stack.top().equals(c)) { // Autoboxed
						stack.pop();
					} else { return false; } // mismatched paren
				}
			}
		}
		return stack.isEmpty(); // not empty => missing right paren
	}

	/**
	 * Ugly, procedural version.
	 */
	public boolean uglyParenMatch() {
		char[] chars = new char[1000]; // ugly magic number
		int pos = 0;
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			switch (c) { // what is going on here?
			case '{' : chars[pos++] = '}'; break;
			case '(' : chars[pos++] = ')'; break;
			case '[' : chars[pos++] = ']'; break;
			case ']' : case ')' : case '}' :
				if (pos == 0) { return false; }
				if (chars[pos-1] == c) { pos--; }
				else { return false; }
				break;
			default : break;
			}
		}
		return pos == 0; // what is this?
	}

	private boolean isLeftParen(char c) {
		return (c == '(') || (c == '[') || (c == '{');
	}
	
	private boolean isRightParen(char c) {
		return (c == ')') || (c == ']') || (c == '}');
	}
	
	private char matchingRightParen(char c) {
		switch (c) {
		case '(' : return ')';
		case '[' : return ']';
		case '{' : return '}';
		}
		return c; // should we throw an exception if we get here?
	}
	
	public static void main(String[] args) {
		parenTestLoop(new LinkStack());
	}
	
	public static void parenTestLoop(StackInterface stack) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			System.out.println("Please enter parenthesized expressions to test");
			System.out.println("(empty line to stop)");
			do {
				line = in.readLine();
				System.out.println(new ParenMatch(line, stack).reportMatch());
			} while(line != null && line.length() > 0);
			System.out.println("bye!");
		} catch (IOException err) {
		}
	}
	
	/**
	 * Returns a printable result.
	 */
	public String reportMatch() {
		if (line == null) { return ""; }
		return "\"" + line + "\" is"
		+ (this.parenMatch() ? " " : " not ")
		+ "balanced";
	}

}
