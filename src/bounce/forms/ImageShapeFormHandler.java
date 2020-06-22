package bounce.forms;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ImageShapeFormHandler implements FormHandler {

    private ShapeModel _model;
    private NestingShape _parentOfNewShape;
    private File imageFile;
    private int width;
    private int deltaX;
    private int deltaY;

    public ImageShapeFormHandler(ShapeModel model, NestingShape parent) {
        _model = model;
        _parentOfNewShape = parent;
    }

    @Override
    public void processForm(Form form) {
        imageFile = (File)form.getFieldValue(File.class, ImageFormElement.IMAGE);
        width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
        deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
        deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);

        worker.execute();
    }


    private SwingWorker<BufferedImage, Void> worker = new SwingWorker<BufferedImage, Void>() {

        @Override
        protected BufferedImage doInBackground() {
            long startTime = System.currentTimeMillis();

            // Read field values from the form.

            // Load the original image (ImageIO.read() is a blocking call).
            BufferedImage fullImage = null;
            try {
                fullImage = ImageIO.read(imageFile);
            } catch(IOException e) {
                System.out.println("Error loading image.");
            }

            int fullImageWidth = fullImage.getWidth();
            int fullImageHeight = fullImage.getHeight();

            BufferedImage scaledImage = fullImage;

            // Scale the image if necessary.
            if(fullImageWidth > width) {
                double scaleFactor = (double)width / (double)fullImageWidth;
                int height = (int)((double)fullImageHeight * scaleFactor);

                scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaledImage.createGraphics();

                // Method drawImage() scales an already loaded image. The
                // ImageObserver argument is null because we don't need to monitor
                // the scaling operation.
                g.drawImage(fullImage, 0, 0, width, height, null);
            }
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Image loading and scaling took " + elapsedTime + "ms.");

            return scaledImage;
        }
        @Override
        protected void done() {
            // Create the new Shape and add it to the model.
            ImageRectangleShape imageShape = null;
            try {
                imageShape = new ImageRectangleShape(deltaX, deltaY, get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            _model.add(imageShape, _parentOfNewShape);
        }
    };
}
