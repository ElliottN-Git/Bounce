package bounce;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 *
 * @author Ian Warren
 */
public abstract class Shape {
    // === Constants for default values. ===
    protected static final int DEFAULT_X_POS = 0;

    protected static final int DEFAULT_Y_POS = 0;

    protected static final int DEFAULT_DELTA_X = 5;

    protected static final int DEFAULT_DELTA_Y = 5;

    protected static final int DEFAULT_HEIGHT = 35;

    protected static final int DEFAULT_WIDTH = 25;

    protected static final Color DEFAULT_COLOR = Color.black;

    protected static final boolean DEFAULT_FILL = false;

    // ===

    // === Instance variables, accessible by subclasses.
    protected int _x;

    protected int _y;

    protected int _deltaX;

    protected int _deltaY;

    protected int _width;

    protected int _height;

    protected String _text;

    protected NestingShape _parent;

    protected Color _color;

    protected boolean _fill;

    /**
     * Creates a Shape object with default values for instance variables.
     */
    public Shape() {
        this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Creates a Shape object with a specified x and y position.
     */
    public Shape(int x, int y) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Creates a Shape instance with specified x, y, deltaX and deltaY values.
     * The Shape object is created with a default width and height.
     */
    public Shape(int x, int y, int deltaX, int deltaY) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
     * height values.
     */
    public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        _text = null;
        _parent = null;
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height,
                 String text) {
        this(x, y, deltaX, deltaY, width, height);
        this._text = text;
    }

    public Shape(int x, int y, int deltaX, int deltaY, String text) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, text);
    }

    public Shape(boolean fill, Color color, int x, int y, int deltaX, int deltaY, int width, int height) {
        this(x, y, deltaX, deltaY, width, height);
        this._fill = fill;
        this._color = color;
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
        this(x, y, deltaX, deltaY, width, height);
        this._color = color;
        this._text = null;
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
        this(x, y, deltaX, deltaY, width, height, text);
        this._color = color;
    }

    /**
     * Moves this Shape object within the specified bounds. On hitting a
     * boundary the Shape instance bounces off and back into the two-
     * dimensional world.
     *
     * @param width  width of two-dimensional world.
     * @param height height of two-dimensional world.
     */
    public void move(int width, int height) {
        move(0, 0, width, height);
    }

    public void move(int x, int y, int width, int height) {
        int nextX = _x + _deltaX;
        int nextY = _y + _deltaY;


        if (nextY <= y) {
            nextY = y;
            _fill = false;
            _deltaY = -_deltaY;
        } else if (nextY + _height >= height) {
            nextY = height - _height;
            _fill = false;
            _deltaY = -_deltaY;
        }

        if (nextX <= x) {
            nextX = x;
            _fill = true;
            _color = randomColorGenerator();
            _deltaX = -_deltaX;
        } else if (nextX + _width >= width) {
            nextX = width - _width;
            _fill = true;
            _color = randomColorGenerator();
            _deltaX = -_deltaX;
        }

        _x = nextX;
        _y = nextY;
    }

    public Color randomColorGenerator() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }

    public final void paint(Painter painter) {
        doPaint(painter);
        if (_text != null) {
            painter.drawCenteredText(_text, _x + _width / 2, _y
                    + _height / 2);
        }
    }

    protected abstract void doPaint(Painter painter);


    /**
     * Returns this Shape object's x position.
     */
    public int x() {
        return _x;
    }

    /**
     * Returns this Shape object's y position.
     */
    public int y() {
        return _y;
    }

    /**
     * Returns this Shape object's speed and direction.
     */
    public int deltaX() {
        return _deltaX;
    }

    /**
     * Returns this Shape object's speed and direction.
     */
    public int deltaY() {
        return _deltaY;
    }

    /**
     * Returns this Shape's width.
     */
    public int width() {
        return _width;
    }

    /**
     * Returns this Shape's height.
     */
    public int height() {
        return _height;
    }


    public String text() {
        return _text;
    }

    /**
     * Returns a String whose value is the fully qualified name of this class
     * of object. E.g., when called on a RectangleShape instance, this method
     * will return "bounce.RectangleShape".
     */
    @Override
    public String toString() {
        return getClass().getName();
    }

    public NestingShape parent() {
        return _parent;
    }

    public List<Shape> path() {
        List<Shape> rootPath = null;
        if (_parent == null) {
            rootPath = new ArrayList<Shape>();
        } else {
            rootPath = _parent.path();
        }
        rootPath.add(this);
        return rootPath;
    }

    protected void setParent(NestingShape parent) {
        _parent = parent;
    }
}

