package com.mycompany.cquassessment3hotelmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        LoadRecords load = new LoadRecords();
        load.loadCarparkArrayList();
        load.loadRoomArrayList();
        load.loadBookingArrayList();
        load.loadClientArrayList();
        load.loadServiceArrayList();
        load.loadInvoiceArrayList();
        scene = new Scene(loadFXML("MainMenu"), 845, 390);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}