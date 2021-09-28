package p2.stack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineReverser {
	StackInterface stack;
	
	LineReverser() {
		stack = new LinkStack();
	}

	public static void main(String[] args) {
		LineReverser lr = new LineReverser();
		BufferedReader in;
		for (int i = 0; i<args.length; i++){
			// System.out.println(args[i]);
	 		try {
				in = new BufferedReader(new FileReader(args[i]));
				lr.rev(in);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void rev(BufferedReader in) throws IOException {
		String line = in.readLine();
		while (line != null) {
			stack.push(line);
			line = in.readLine();
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.top());
			stack.pop();
		}
	}

}
