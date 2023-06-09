module com.mycompany.cquassessment3hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.cquassessment3hotelmanager to javafx.fxml;
    exports com.mycompany.cquassessment3hotelmanager;
}
