import java.util.List;
import java.util.ArrayList;

/**
 * Illustrates procedural versus OO design.
 * Picture.compute_size() concentrates the logic of computing sizes of shapes
 * in a single location, and uses a switch statement to select the right formula. 
 * By contrast, the size() method is implemented by a hierarchy of classes that
 * each inherit from Shape, and the right method is selected by the object itself.
 * 
 * @author oscar
 *
 */
public class Picture {
	List<Shape> shapes;

	Picture() {
		shapes = new ArrayList<Shape>();
	}
	
	public static void main(String[] args) {
		Picture myPicture = new Picture();
		myPicture.add(new Square(3,3,3)); // (x,y,width)
		myPicture.add(new Rectangle(5,9,5,3)); // (x,y,width,height)
		myPicture.add(new Circle(12,3,3)); // (x,y,radius)
		
		System.out.println("My picture has size " + myPicture.size());
		System.out.println("My picture has size " + myPicture.compute_size());
	}
	
	void add(Shape shape) {
		shapes.add(shape);
	}
	
	double size() {
		double total = 0;
		for (Shape shape : shapes) {
			total += shape.size();
		}
		return total;
	}
	
	double compute_size() {
		double total = 0;
		for (Shape shape : shapes) {
			switch (shape.kind()) {
			case SQUARE:
				Square square = (Square) shape;
				total += square.width * square.width;
				break;
			case RECTANGLE:
				Rectangle rectangle = (Rectangle) shape;
				total += rectangle.width * rectangle.height;
				break;
			case CIRCLE:
				Circle circle = (Circle) shape;
				total += java.lang.Math.PI * circle.radius * circle.radius / 2;
				break;
			}
		}
		return total;
	}
}
