
public abstract class Shape {
	double x, y; // start position (bottom-left, center etc.)
	
	public enum Kind { SQUARE, RECTANGLE, CIRCLE };
	
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract double size();
	
	public abstract Kind kind();

}
