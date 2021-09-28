package abstractFactory;


public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		int systemType = 1; // replace this with a read from a config file for example
		GUIFactory factory = GUIFactory.getFactory(systemType);
		Button button = factory.createButton();
		button.paint();

	}

}
