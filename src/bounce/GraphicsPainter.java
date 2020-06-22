package bounce;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 *
 * @author Ian Warren
 */
public class GraphicsPainter implements Painter {
    // Delegate object.
    private Graphics _g;

    /**
     * Creates a GraphicsPainter object and sets its Graphics delegate.
     */
    public GraphicsPainter(Graphics g) {
        this._g = g;
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        _g.drawRect(x, y, width, height);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        _g.getColor();
        _g.fillRect(x, y, width, height);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        _g.drawOval(x, y, width, height);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        _g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawImage(Image image, int x, int y, int width, int height, ImageObserver imgObs) {
        _g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void setColor(Color color) {
        _g.setColor(color);
    }

    @Override
    public Color getColor() {
        return _g.getColor();
    }

    @Override
    public void drawCenteredText(String text, int x, int y) {
        FontMetrics fm = _g.getFontMetrics();
        int ascent = fm.getAscent();
        int descent = fm.getDescent();

        int xPos = x - fm.stringWidth(text) / 2;
        int yPos = y;

        if (ascent > descent)
            yPos += (ascent - descent) / 2;
        else if (ascent < descent)
            yPos -= (descent - ascent) / 2;

        _g.drawString(text, xPos, yPos);
    }

    @Override
    public void translate(int x, int y) {
        _g.translate(x, y);
    }
}
