package visitorPattern;

public class Wheel implements Visitable {
	private String name;
	Wheel(String name){
		this.name = name;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);

	}

	public String getName() {
		return name;
	}

}
