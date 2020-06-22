package bounce.views;

import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.ArrayList;


public class Task2 extends Task1 implements ShapeModelListener {

    private ArrayList<TreeModelListener> tmListeners = new ArrayList<>();

    public Task2(ShapeModel shapeModel) {
        super(shapeModel);
    }

    @Override
    public void addTreeModelListener(TreeModelListener tML) {
        tmListeners.add(tML);
    }

    @Override
    public void update(ShapeModelEvent event) {

        int[] indices = new int[1];
        Object[] children = new Object[1];
        Object[] path;

        if (event.eventType() == ShapeModelEvent.EventType.ShapeAdded) {
            for (TreeModelListener treeModelListener : tmListeners) {
                indices[0] = event.index();
                children[0] = event.operand();
                path = event.parent().path().toArray();

                treeModelListener.treeNodesInserted(new TreeModelEvent(event.source(), path, indices, children));
            }
        } else if (event.eventType() == ShapeModelEvent.EventType.ShapeRemoved) {
            for (TreeModelListener treeModelListener : tmListeners) {
                indices[0] = event.index();
                children[0] = event.operand();
                path = event.parent().path().toArray();

                treeModelListener.treeNodesRemoved(new TreeModelEvent(event.source(), path, indices, children));
            }
        }
    }
}
