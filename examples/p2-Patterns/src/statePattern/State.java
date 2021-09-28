package statePattern;

public abstract class State {
	protected Controller controller;
	public void open() { this.defaultAction(); }
	public void close() { this.defaultAction(); }
	public void lock() { this.defaultAction(); }
	public void startUnlock() { this.defaultAction(); }
	public void errorEntered() { this.defaultAction(); }
	public void combinationEntered() { this.defaultAction(); }
	public static State enter(Controller controller){
		return null;
	}
	protected void defaultAction() {
		controller.invalidStateChange();
	}
}
