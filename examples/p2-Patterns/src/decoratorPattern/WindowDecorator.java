package decoratorPattern;

abstract class WindowDecorator implements Window {
	protected Window decoratedWindow;
	
	public WindowDecorator (Window decoratedWindow) {
        this.decoratedWindow = decoratedWindow;
    }
	
}
//	 the first concrete decorator which adds vertical scrollbar functionality
	