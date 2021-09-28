package statePattern;

public class Opened extends State {
	private static Opened instance;
	
	private Opened(Controller controller){
		this.controller = controller;
	}
	
	public static State enter(Controller controller){
		if(instance == null) instance = new Opened(controller); // lazy evalution
		return instance;
	}
	
	public void close(){
		controller.changeState(Closed.enter(controller));
	}
}
