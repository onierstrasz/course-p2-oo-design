package statePattern;

public class AwaitingCombination extends State {
	private static AwaitingCombination instance;
	
	private AwaitingCombination(Controller controller){
		this.controller = controller;
	}
	
	public static State enter(Controller controller){
		if(instance == null) instance = new AwaitingCombination(controller); // lazy evalution
		return instance;
	}
	
	public void combinationEntered(){
		controller.changeState(Closed.enter(controller));
	}
	
	public void errorEntered(){
		controller.changeState(Locked.enter(controller));
	}
}
