package statePattern;

public class Locked extends State {

	private static Locked instance;
	
	private Locked(Controller controller){
		this.controller = controller;
	}
	
	public static State enter(Controller controller){
		if(instance == null) instance = new Locked(controller);
		return instance;
	}
	
	public void startUnlock(){
		controller.changeState(AwaitingCombination.enter(controller));
	}

}
