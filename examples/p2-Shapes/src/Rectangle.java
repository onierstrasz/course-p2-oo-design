
public class Rectangle extends Shape {
	
	double width, height;

	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public double size() {
		return width*height;
	}

	@Override
	public Kind kind() {
		return Kind.RECTANGLE;
	}

}
