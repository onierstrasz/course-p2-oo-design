package visitorPattern;

/**
 * @author greevy
 * 
 * The visitor pattern is a way of separating the algorithm from the object structure.
 * You can add new operations to existing structures without modifying the structures.
 *
 * Use the structure of the element classes, each of which has an accept method that takes a 
 * visitor object as an argument. A Visitor is an interface that has a visit method for each element 
 * of the class.
 * 
 * Separate concrete visitor classes can be written that perform some particular operations.
 */
public class VisitorDemo {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Car car = new Car();
		Visitor visitor = new PrintVisitor();
		car.accept(visitor);
	}

}
