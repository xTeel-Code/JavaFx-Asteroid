package sk.ukf.ot.du;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/** Planéta, ktorá obieha po kružnici okolo stredu, kde bola vytvorená. */
public class Planet extends SpaceObject {
    public static final double RADIUS = 22;

    private final double centerX;
    private final double centerY;
    private final double orbitRadius;
    private final double speed;
    private final Color color;
    private double angle;

    public Planet(double centerX, double centerY, double orbitRadius, double speed, Color color) {
        super(centerX + orbitRadius, centerY);
        this.centerX = centerX;
        this.centerY = centerY;
        this.orbitRadius = orbitRadius;
        this.speed = speed;
        this.color = color;
        createView();
        updateView();
    }

    @Override
    protected void createView() {
        Circle body = new Circle(RADIUS);
        body.setFill(new RadialGradient(0, 0, -0.3, -0.3, 1.2, true, CycleMethod.NO_CYCLE,
                new Stop(0, color.brighter()), new Stop(1, color.darker().darker())));

        Ellipse ring = new Ellipse(RADIUS * 1.7, RADIUS * 0.45);
        ring.setFill(null);
        ring.setStroke(Color.web("#e8d9a8", 0.8));
        ring.setStrokeWidth(3);
        ring.setRotate(-20);

        view.getChildren().addAll(body, ring);
    }

    @Override
    public void move(double width, double height) {
        angle += speed;
        x = centerX + orbitRadius * Math.cos(angle);
        y = centerY + orbitRadius * Math.sin(angle);
        updateView();
    }
}
