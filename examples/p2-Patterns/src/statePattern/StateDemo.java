package statePattern;

import java.io.IOException;

public class StateDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Controller garageController = new Controller(System.in,System.out);
		garageController.run();
	}

}
