module sk.ukf.ot.du {
    requires javafx.controls;
    requires javafx.fxml;


    opens sk.ukf.ot.du to javafx.fxml;
    exports sk.ukf.ot.du;
}