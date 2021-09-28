package annotations;

/**
 * Demo of model-driven development using Java Annotations
 * 
 * @author Adrian Kuhn, 2007
 *
 */
public class Main {

	public static void main(String[] args) {
		Book book = new Book();
		book.setTitle("Design Patterns");
		book.setAuthor("Erich Gamma, Richard Helm, John Vlissides, Ralph Johnson");
		InspectorFrame inspector = new InspectorFrame(book);
		inspector.setVisible(true);
		// (new InspectorFrame(new Address())).setVisible(true);
	}

}
