package sk.ukf.ot.du;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceController {
    private static final Color[] PLANET_COLORS = {
            Color.web("#4a90d9"), Color.web("#d9734a"), Color.web("#5bbf6a"),
            Color.web("#c9a64a"), Color.web("#a35bd9")
    };

    @FXML
    private Pane spacePane;

    private final List<SpaceObject> objects = new ArrayList<>();
    private final Random random = new Random();

    @FXML
    private void initialize() {
        // obsah nevytŕča mimo plochy
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(spacePane.widthProperty());
        clip.heightProperty().bind(spacePane.heightProperty());
        spacePane.setClip(clip);

        for (int i = 0; i < 120; i++) {
            Circle star = new Circle(random.nextDouble() * 1.5 + 0.3, Color.web("#ffffff", random.nextDouble() * 0.6 + 0.2));
            star.setCenterX(random.nextDouble() * 1600);
            star.setCenterY(random.nextDouble() * 1000);
            star.setMouseTransparent(true);
            spacePane.getChildren().add(star);
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (SpaceObject object : objects) {
                    object.move(spacePane.getWidth(), spacePane.getHeight());
                }
            }
        }.start();
    }

    @FXML
    private void onAddPlanet() {
        double orbitRadius = 30 + random.nextDouble() * 50;
        double margin = orbitRadius + Planet.RADIUS * 1.7;
        double speed = (0.01 + random.nextDouble() * 0.02) * (random.nextBoolean() ? 1 : -1);
        Color color = PLANET_COLORS[random.nextInt(PLANET_COLORS.length)];
        add(new Planet(randomX(margin), randomY(margin), orbitRadius, speed, color));
    }

    @FXML
    private void onAddAsteroid() {
        double dx = (1.5 + random.nextDouble() * 2) * (random.nextBoolean() ? 1 : -1);
        double dy = (1.5 + random.nextDouble() * 2) * (random.nextBoolean() ? 1 : -1);
        add(new Asteroid(randomX(Asteroid.RADIUS), randomY(Asteroid.RADIUS), dx, dy));
    }

    @FXML
    private void onAddSatellite() {
        double range = 40 + random.nextDouble() * 60;
        double speed = 1 + random.nextDouble() * 1.5;
        add(new Satellite(randomX(30), randomY(range + Satellite.HALF_HEIGHT), range, speed));
    }

    @FXML
    private void onAsteroidsRed() {
        for (SpaceObject object : objects) {
            if (object instanceof Asteroid) {
                ((Asteroid) object).setColor(Color.RED);
            }
        }
    }

    @FXML
    private void onSatellitesRight() {
        for (SpaceObject object : objects) {
            if (object instanceof Satellite) {
                ((Satellite) object).moveRight(20);
            }
        }
    }

    private void add(SpaceObject object) {
        objects.add(object);
        spacePane.getChildren().add(object.getView());
    }

    private double randomX(double margin) {
        return randomBetween(margin, spacePane.getWidth() - margin);
    }

    private double randomY(double margin) {
        return randomBetween(margin, spacePane.getHeight() - margin);
    }

    private double randomBetween(double min, double max) {
        if (max <= min) {
            return (min + max) / 2;
        }
        return min + random.nextDouble() * (max - min);
    }
}
