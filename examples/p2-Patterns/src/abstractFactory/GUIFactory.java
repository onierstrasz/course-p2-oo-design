package abstractFactory;

abstract class GUIFactory {

	static final int OSX = 1;
	static final int WIN = 2;
	
	public static GUIFactory getFactory(int aType) {
		
		if (aType == OSX)
			return new OSXFactory();
		else
			return  new WinFactory();
			
	//	return null;
	}
	public abstract Button createButton();
	
}
