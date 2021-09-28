package generics;

import java.awt.Color;

/**
 * Dummy class for generics demo.
 * 
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: Stone.java 17002 2008-03-13 10:20:29Z oscar $
 */
public class Stone {
	
	private Color color;

	Stone(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public String toString() {
		return "stone(" + color + ")";
	}

}
