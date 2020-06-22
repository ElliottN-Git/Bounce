package bounce;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Interface to represent a type that offers primitive drawing methods.
 *
 * @author Ian Warren
 */
public interface Painter {
    /**
     * Draws a rectangle. Parameters x and y specify the top left corner of the
     * oval. Parameters width and height specify its width and height.
     */
    public void drawRect(int x, int y, int width, int height);

    public void fillRect(int x, int y, int width, int height);

    /**
     * Draws an oval. Parameters x and y specify the top left corner of the
     * oval. Parameters width and height specify its width and height.
     */
    public void drawOval(int x, int y, int width, int height);

    /**
     * Draws a line. Parameters x1 and y1 specify the starting point of the
     * line, parameters x2 and y2 the ending point.
     */
    public void drawLine(int x1, int y1, int x2, int y2);


    public void drawImage(Image image, int x, int y, int width, int height, ImageObserver imgObs);

    /**
     * Sets the color of this Painter to the given value.
     */
    public void setColor(Color color);

    /**
     * Gets the current color of this Painter.
     */
    public Color getColor();

    /**
     * Draws a text string. Parameters x and y represent the centre point of a
     * bounding box in which the text is to be painted.
     */
    public void drawCenteredText(String text, int x, int y);

    public void translate(int x, int y);
}
