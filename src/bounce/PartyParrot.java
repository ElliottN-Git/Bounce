package bounce;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class PartyParrot extends Shape {

    private BufferedImage[] frames = new BufferedImage[24];
    private String direction;
    private int frame = 1;

    public PartyParrot() {
        super(200, 200, 2, 2, 80, 80);
        this.direction = "right";
        loadImages();
    }

    public PartyParrot(int x, int y) {
        super(x, y);
        this.direction = "right";
        loadImages();
    }

    public PartyParrot(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
        if (deltaX >= 0) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }
        loadImages();
    }

    public PartyParrot(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
        if (deltaX >= 0) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }
        loadImages();
    }

    public PartyParrot(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        super(x, y, deltaX, deltaY, width, height, text);
        if (deltaX >= 0) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }
        loadImages();
    }

    /**
     * Method used in each of the constructors for loading in the frames
     * for the bird animation.
     * Frames loaded to frames array.
     */
    private void loadImages() {
        try {
            for (int i = 0; i < 24; i++) {
                BufferedImage img = ImageIO.read(getClass().getResource("bird/frame" + (i + 1) + ".png"));
                frames[i] = img;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Paints the PartyParrot using the set of frames as determined
     * by the direction variable, i.e. left or right (because birds can't fly backwards!)
     *
     * @param painter
     */
    @Override
    public void doPaint(Painter painter) {
        switch (direction) {
            case "right":
                if (frame == 12) {
                    painter.drawImage(frames[frame - 1], _x, _y, _width, _height, null);
                    frame = 1;
                    break;
                } else {
                    painter.drawImage(frames[frame - 1], _x, _y, _width, _height, null);
                    frame++;
                    break;
                }
            case "left":
                if (frame == 12) {
                    painter.drawImage(frames[frame + 11], _x, _y, _width, _height, null);
                    frame = 1;
                    break;
                } else {
                    painter.drawImage(frames[frame + 11], _x, _y, _width, _height, null);
                    frame++;
                    break;
                }
        }
    }


    /**
     * Moves the PartParrot according to deltaX and deltaY and bounces off the bounds
     * of it's container's dimensions (width and height).
     * Also changes the direction variable.
     *
     * @param width  width of two-dimensional world.
     * @param height height of two-dimensional world.
     */
    @Override
    public void move(int width, int height) {
        int nextX = _x + _deltaX;
        int nextY = _y + _deltaY;


        if (nextX > _x) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }

        if (nextY <= 0) {
            nextY = 0;
            _fill = false;
            _deltaY = -_deltaY;
        } else if (nextY + _height >= height) {
            nextY = height - _height;
            _fill = false;
            _deltaY = -_deltaY;
        }

        if (nextX <= 0) {
            nextX = 0;
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
}
