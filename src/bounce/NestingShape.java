package bounce;

import java.util.ArrayList;

public class NestingShape extends Shape {

    private ArrayList<Shape> nestedShapes = new ArrayList<>();


    /**
     * Constructors for NestingShape
     */
    public NestingShape() {
        super();
    }

    public NestingShape(int x, int y) {
        super(x, y);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }


    /**
     * Overrides the doPaint method in Shape
     * paints the outer rectangle of the NestingShape then adjusts the
     * coordinate 'box' to be at the same location as the outer rectangle
     * using Graphics.translate().
     * Moves and paints the shapes contained within nestedShapes list.
     * Finally removes the coordinate adjustment
     *
     * @param painter
     */
    @Override
    protected void doPaint(Painter painter) {
        painter.drawRect(_x, _y, _width, _height);
        painter.translate(this._x, this._y);
        for (Shape shape : this.nestedShapes) {
            shape.move(_width, _height);
            shape.paint(painter);
        }
        painter.translate(-this._x, -this._y);
    }


    /**
     * Attempts to add a Shape to a NestingShape object. If successful, a
     * two-way link is established between the NestingShape and the newly
     * added Shape. Note that this method has package visibility.
     *
     * @param shape The shape to be added
     * @throws IllegalArgumentException
     */
    /**
     * if an attempt is made to add a Shape
     * to a NestingShape instance where the Shape argument is already a child
     * within a NestingShape instance. An IllegalArgumentException is also
     * thrown when an attempt is made to add a Shape that will not fit within
     * the bounds of the proposed NestingShape object.
     */
    void add(Shape shape) throws IllegalArgumentException {
        if ((shape.parent() == null) && (this._width > shape._width) && (this._height > shape._height)
                && ((this._x + this._width) > (shape._x + shape._width)) && ((this._y + this._height) > (shape._y + shape._height))) {
            nestedShapes.add(shape);
            shape._parent = this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    void remove(Shape shape) {
        nestedShapes.remove(shape);
        shape._parent = null;
    }

    public Shape shapeAt(int index) throws IndexOutOfBoundsException {
        Shape shapeAtIndex = null;
        shapeAtIndex = nestedShapes.get(index);
        return shapeAtIndex;
    }

    public int shapeCount() {
        return nestedShapes.size();
    }

    public int indexOf(Shape shape) {
        if (!nestedShapes.contains(shape)) {
            return -1;
        } else {
            return nestedShapes.indexOf(shape);
        }
    }

    public boolean contains(Shape shape) {
        return shape.parent() == this;
    }
}


