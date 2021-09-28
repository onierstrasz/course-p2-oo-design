package compositePattern;

public class Application {
	public static void main(String[] args) {
		Composite switzerland = new Composite("Switzerland");
		Leaf bern = new Leaf("Bern");
		Leaf zuerich = new Leaf("Zuerich");
		switzerland.addComponent(bern);
		switzerland.addComponent(zuerich);
		Composite europe = new Composite("Europe");
		europe.addComponent(switzerland);
		System.out.println(europe.name());
	}

}
