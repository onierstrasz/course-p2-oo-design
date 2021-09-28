
public class Square extends Shape {

	double width;
	
	public Square(double x, double y, double width) {
		super(x, y);
		this.width = width;
	}

	@Override
	public double size() {
		return width*width;
	}

	@Override
	public Kind kind() {
		return Kind.SQUARE;
	}

}
