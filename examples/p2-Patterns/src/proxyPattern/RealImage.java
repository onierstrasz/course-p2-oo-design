package proxyPattern;

public class RealImage implements Image {
	private String filename;
	public RealImage(String filename) {
		this.filename = filename;
		System.out.println("Loading "+filename);
	}
	
	public void displayImage() {
		System.out.println("Displaying "+filename);
	}

}
