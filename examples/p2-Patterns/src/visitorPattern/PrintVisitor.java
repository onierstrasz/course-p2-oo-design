package visitorPattern;

public class PrintVisitor implements Visitor {

	public void visit(Wheel wheel) {
		System.out.println("Visiting " + wheel.getName());

	}

	public void visit(Engine engine) {
		System.out.println("Visiting engine");

	}

	public void visit(Body body) {
		System.out.println("Visiting body");

	}

	public void visit(Car car) {
		System.out.println("Visiting car");

	}

}
