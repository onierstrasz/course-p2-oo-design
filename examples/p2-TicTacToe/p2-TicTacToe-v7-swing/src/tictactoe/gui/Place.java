package tictactoe.gui;

import java.awt.*;
import javax.swing.*;
import tictactoe.Player;

/**
 * Represents the view of a single place on the BoardGame.
 * An instance of PlaceListener forwards mouse clicks to a Player.
 * Here we assume that Players have the mark 'X' or 'O'.
 *
 * @author $Author: oscar $
 * @version $Id: Place.java 16615 2008-03-01 17:08:43Z oscar $
 */
@SuppressWarnings("serial")
public class Place extends JPanel {
	protected final int col;
	protected final int row;
	
	protected final String imageDir = "tictactoe/gui/images";
	protected Image image;
	protected final Image oImage;
	protected final Image xImage;
	protected final Image blankImage;
	
	/**
	 * A Place knows its row and column number.
	 * It also knows how to display either an X or O image
	 * when its state changed.
	 */
	Place(int myCol, int myRow) {
		col = myCol;
		row = myRow;
		this.xImage = getImage(imageDir + "/cross.gif");
		this.oImage = getImage(imageDir + "/not.gif");
		this.blankImage = getImage(imageDir + "/blank.gif");
		this.clearImage();
	}
	
	protected Image getImage(String imageFile){
		// Image image = Toolkit.getDefaultToolkit().createImage(imageFile);
		// assert image != null;
		ClassLoader cl = this.getClass().getClassLoader();
		Image image = Toolkit.getDefaultToolkit().createImage(cl.getResource(imageFile));
		return image;
	}
	
	/**
	 * Draws a box along its edge, and, if an image is defined,
	 * draws that too (inset 10% from the border).
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Rectangle rect = g.getClipBounds();
		int h = rect.height;
		int w = rect.width;
		int offset = w/10;
		g.drawRect(0,0,w,h);
		if (image != null) {
			g.drawImage(image, offset, offset, w-2*offset, h-2*offset, this);
		}
	}
	
	/**
	 * Called by PlaceListener to determine in which location
	 * the mouse has been clicked.
	 * @see PlaceListener
	 */
	public int getRow() { return row; }
	public int getCol() { return col; }
	
	/**
	 * Called when the BoardGame changes state.
	 * Note that we hard-wire the interpretation of the mark
	 * as either 'X' or 'O'.  This would have to be changed
	 * if the application were extended to deal with other
	 * kinds of Players.
	 */
	public void setMove(Player player) {
		if (player.mark() == 'X') {
			image = xImage;
		} else {
			image = oImage;
		}
		repaint();
	}
	
	public void clearImage() {
		setBackground(Color.white);
		image = blankImage;
		repaint();
	}
}
