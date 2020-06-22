package bounce;

public class GemShape extends Shape {

    public GemShape() {
        super(200, 200, 2, 2, 40, 40);
    }

    public GemShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX, deltaY, width, height, text);
    }

    /**
     * Paints the GemShape as a hexagon if width and height are both over 40.
     * Paints the GemShape as a diamond if width or height are below 40.
     *
     * @param painter
     */
    @Override
    public void doPaint(Painter painter) {
        if ((_width >= 40) && (_height >= 40)) {
            painter.drawLine(_x, _y + ((_height / 3) * 2), _x, _y + (_height / 3));
            painter.drawLine(_x, _y + (_height / 3), _x + (_height / 2), _y);
            painter.drawLine(_x + (_width / 2), _y, _x + _width, _y + (_height / 3));
            painter.drawLine(_x + _width, _y + (_height / 3), _x + _width, _y + ((_height / 3) * 2));
            painter.drawLine(_x + _width, _y + ((_height / 3) * 2), _x + (_width / 2), _y + _height);
            painter.drawLine(_x + (_width / 2), _y + _height, _x, _y + ((_height / 3) * 2));
        } else {
            painter.drawLine(_x, _y + (_height / 2), _x + (_width / 2), _y);
            painter.drawLine(_x + (_width / 2), _y, _x + _width, _y + (_height / 2));
            painter.drawLine(_x + _width, _y + _height / 2, _x + _width / 2, _y + _height);
            painter.drawLine(_x + (_width / 2), _y + (_height), _x, _y + (_height / 2));
        }
    }
}
