module projects.oopfinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;

    opens projects.oopfinalproject to javafx.fxml;
    exports projects.oopfinalproject;
}
