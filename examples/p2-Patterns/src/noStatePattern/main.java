package noStatePattern;
import java.io.*;
public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Controller aController = new Controller(System.in,System.out);
		aController.run();
	}

}
