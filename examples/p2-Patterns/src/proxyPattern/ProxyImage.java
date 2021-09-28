package proxyPattern;

public class ProxyImage implements Image {
	private String filename; 
	private Image image;
	
	public ProxyImage(String filename){
		this.filename = filename;
	}
	public void displayImage() {
		if (image == null) {
			image = new RealImage(filename); // load only on demand
		}
		image.displayImage();
	}

}
