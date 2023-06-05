module projects.oopfinalproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens projects.oopfinalproject to javafx.fxml;
    exports projects.oopfinalproject;
}
