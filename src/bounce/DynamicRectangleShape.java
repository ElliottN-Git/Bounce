package bounce;

import java.awt.*;
import java.util.Random;

public class DynamicRectangleShape extends Shape {

    public DynamicRectangleShape() {
        super();
    }

    public DynamicRectangleShape(boolean fill, Color color, int x, int y, int deltaX, int deltaY, int width, int height) {
        super(fill, color, x, y, deltaX, deltaY, width, height);
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
        super(x, y, deltaX, deltaY, width, height, color);
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
        super(x, y, deltaX, deltaY, width, height, text, color);
    }

    @Override
    public void doPaint(Painter painter) {
        painter.drawRect(_x, _y, _width, _height);
        if (_fill) {
            painter.setColor(_color);
            painter.fillRect(_x, _y, _width, _height);
        } else {
            painter.setColor(new Color(0, 0, 0, 0));
            painter.fillRect(_x, _y, _width, _height);
        }
        painter.setColor(Color.black);
    }
}
