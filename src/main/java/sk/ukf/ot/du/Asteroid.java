package sk.ukf.ot.du;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/** Asteroid, ktorý letí diagonálne a odráža sa od okrajov plochy. */
public class Asteroid extends SpaceObject {
    public static final double RADIUS = 16;

    private double dx;
    private double dy;
    private Polygon body;

    public Asteroid(double x, double y, double dx, double dy) {
        super(x, y);
        this.dx = dx;
        this.dy = dy;
        createView();
        updateView();
    }

    @Override
    protected void createView() {
        // nepravidelný mnohouholník pripomínajúci kameň
        double[] scale = {1.0, 0.8, 0.95, 0.7, 1.0, 0.85, 0.75, 0.9};
        body = new Polygon();
        for (int i = 0; i < scale.length; i++) {
            double a = 2 * Math.PI * i / scale.length;
            body.getPoints().addAll(RADIUS * scale[i] * Math.cos(a), RADIUS * scale[i] * Math.sin(a));
        }
        body.setFill(Color.GRAY);
        body.setStroke(Color.DIMGRAY);

        Circle crater1 = new Circle(-4, -3, 3, Color.web("#000000", 0.25));
        Circle crater2 = new Circle(5, 4, 2, Color.web("#000000", 0.25));

        view.getChildren().addAll(body, crater1, crater2);
    }

    @Override
    public void move(double width, double height) {
        x += dx;
        y += dy;
        if (x - RADIUS < 0 || x + RADIUS > width) {
            dx = -dx;
            x = Math.max(RADIUS, Math.min(x, width - RADIUS));
        }
        if (y - RADIUS < 0 || y + RADIUS > height) {
            dy = -dy;
            y = Math.max(RADIUS, Math.min(y, height - RADIUS));
        }
        updateView();
    }

    public void setColor(Color color) {
        body.setFill(color);
    }
}
