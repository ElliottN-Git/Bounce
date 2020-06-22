package bounce;

import javax.swing.*;
import java.awt.Image;

/**
 * Class to represent a rectangle that displays an image.
 * 
 * @author Ian Warren
 * 
 */
public class ImageRectangleShape extends RectangleShape {

	private Image _picture;
	
	public ImageRectangleShape(int deltaX, int deltaY, Image image) {
		// Derive the shape's width and height from the image.
		super(2, 2, deltaX, deltaY, image.getWidth(null), image.getHeight(null));
		
		_picture = image;
	}

	public ImageRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	@Override
	public void doPaint(Painter painter) {
		painter.drawImage(_picture,_x,_y,_width,_height, null);
	}
}

