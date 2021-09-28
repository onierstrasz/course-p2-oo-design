package visitorPattern;

public class Body implements Visitable {

	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

}
