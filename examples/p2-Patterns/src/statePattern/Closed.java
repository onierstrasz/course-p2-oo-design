package statePattern;

public class Closed extends State {
	private static Closed instance;
	
	private Closed(Controller controller){
		this.controller = controller;
	}
	
	public static State enter(Controller controller)
	{
		if (instance == null) instance = new Closed(controller);
		return instance;
	}

	public void open(){
		controller.changeState(Opened.enter(controller));
	}
	
	public void lock(){
		controller.changeState(Locked.enter(controller));
	}
	
}
