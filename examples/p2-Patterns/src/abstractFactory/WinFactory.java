package abstractFactory;



public class WinFactory extends GUIFactory {

	public Button createButton() {
		// TODO Auto-generated method stub
		return new WinButton();
	}

	

}
