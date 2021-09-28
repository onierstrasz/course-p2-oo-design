package abstractFactory;



public class OSXFactory extends GUIFactory{
	public Button createButton() {
		// TODO Auto-generated method stub
		return new OSXButton();
	}

}
