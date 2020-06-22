package bounce.views;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Task1 implements TreeModel {

    private ShapeModel shapeModel;

    private ArrayList<TreeModelListener> tmListeners = new ArrayList<>();

    public Task1() {}

    public Task1(ShapeModel model){ this.shapeModel = model; }

    @Override
    public Object getRoot() {
        return this.shapeModel.root();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof NestingShape) {
            if (index >= 0 && index < getChildCount(parent)) {
                return ((NestingShape) parent).shapeAt(index);
            }
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof NestingShape) {
            return ((NestingShape) parent).shapeCount();
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return !(node instanceof NestingShape);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof NestingShape) {
            return ((NestingShape) parent).indexOf((Shape) child);
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener tML) {
        tmListeners.add(tML);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tML) {
        tmListeners.remove(tML);
    }
}
