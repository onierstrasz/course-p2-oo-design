package proxyPattern;

import java.util.ArrayList;

public class ProxyExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Image> images = new ArrayList<Image>();
				images.add(new ProxyImage("HiRes_10MB_Photo1"));
				images.add(new ProxyImage("HiRes_10MB_Photo2"));
				images.add(new ProxyImage("HiRes_10MB_Photo3"));
				
				images.get(0).displayImage();
				images.get(1).displayImage();
				images.get(0).displayImage();
	}

}

