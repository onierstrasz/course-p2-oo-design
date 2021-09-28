
public class Circle extends Shape {
	
	double radius;

	public Circle(double x, double y, double radius) {
		super(x, y);
		this.radius = radius;
	}

	@Override
	public double size() {
		return java.lang.Math.PI * radius * radius / 2;
	}

	@Override
	public Kind kind() {
		return Kind.CIRCLE;
	}

}
