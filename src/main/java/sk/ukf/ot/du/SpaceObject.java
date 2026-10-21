package sk.ukf.ot.du;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Spoločný predok všetkých vesmírnych objektov.
 * Pozícia (x, y) určuje stred objektu na ploche.
 */
public abstract class SpaceObject {
    protected double x;
    protected double y;
    protected final Group view = new Group();

    protected SpaceObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Vytvorí grafické znázornenie objektu (tvary okolo bodu 0,0). */
    protected abstract void createView();

    /** Posunie objekt o jeden krok v rámci plochy s danými rozmermi. */
    public abstract void move(double width, double height);

    protected void updateView() {
        view.setTranslateX(x);
        view.setTranslateY(y);
    }

    public Node getView() {
        return view;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
