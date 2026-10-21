package sk.ukf.ot.du;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/** Satelit, ktorý sa pohybuje hore a dole medzi hornou a dolnou hranicou svojej dráhy. */
public class Satellite extends SpaceObject {
    public static final double HALF_HEIGHT = 14;

    private final double minY;
    private final double maxY;
    private double dy;

    public Satellite(double x, double y, double range, double speed) {
        super(x, y);
        this.minY = y - range;
        this.maxY = y + range;
        this.dy = speed;
        createView();
        updateView();
    }

    @Override
    protected void createView() {
        Rectangle body = new Rectangle(-8, -10, 16, 20);
        body.setFill(Color.SILVER);
        body.setStroke(Color.DARKGRAY);

        Rectangle leftPanel = new Rectangle(-30, -6, 20, 12);
        Rectangle rightPanel = new Rectangle(10, -6, 20, 12);
        for (Rectangle panel : new Rectangle[]{leftPanel, rightPanel}) {
            panel.setFill(Color.ROYALBLUE);
            panel.setStroke(Color.LIGHTSKYBLUE);
        }

        Line antenna = new Line(0, -10, 0, -HALF_HEIGHT - 4);
        antenna.setStroke(Color.LIGHTGRAY);
        Circle tip = new Circle(0, -HALF_HEIGHT - 4, 2, Color.ORANGERED);

        view.getChildren().addAll(leftPanel, rightPanel, body, antenna, tip);
    }

    @Override
    public void move(double width, double height) {
        y += dy;
        double top = Math.max(minY, HALF_HEIGHT + 4);
        double bottom = Math.min(maxY, height - HALF_HEIGHT);
        if (y < top || y > bottom) {
            dy = -dy;
            y = Math.max(top, Math.min(y, bottom));
        }
        updateView();
    }

    /** Posunie satelit vodorovne doprava o zadaný počet pixelov. */
    public void moveRight(double pixels) {
        x += pixels;
        updateView();
    }
}
