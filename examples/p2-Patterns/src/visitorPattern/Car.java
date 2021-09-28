package visitorPattern;

public class Car implements Visitable {
	private Engine engine = new Engine();
	private Body body = new Body();
	private Wheel[] wheels = { new Wheel("front left"), new Wheel("front right"),
			new Wheel("back left"), new Wheel("back right")};
	
	public Engine getEngine(){
		return this.engine;
	}
	public Body getBody(){
		return this.body;
	}
	public Wheel[] getWheels() {
		return this.wheels;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		engine.accept(visitor);
		body.accept(visitor);
		for(int i = 0; i < wheels.length; ++i)
			wheels[i].accept(visitor);

	}

}
