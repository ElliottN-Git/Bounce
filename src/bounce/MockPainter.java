package bounce;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Ian Warren
 * 
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();

	private Color color = Color.black;

	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}

	public void fillRect(int x, int y, int width, int height) {
		_log.append("(fill " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}

	public void drawImage(Image image, int x, int y, int width, int height, ImageObserver imgObs) {

	}

	/**
	 * Sets the color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns the color.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Logs the drawCenteredText call.
	 */
	public void drawCenteredText(String text, int x, int y) {
		_log.append("(centered-text " + text + "," + x + "," + y + ")");
	}

	public void translate(int x, int y) {
		_log.append("(translate " + x + "," + y + ")");
	}
}