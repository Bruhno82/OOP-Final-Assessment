/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projects.oopfinalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class FXMLBookingController implements Initializable {


    @FXML
    private Button buttonUpdateBooker;
    @FXML
    private Button buttonSearchBooking;
    @FXML
    private Button buttonCreateBooking;
    @FXML
    private Button buttonCancelBooking;
    @FXML
    private Button buttonExit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleButtonUpdateBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonSearchBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonCreateBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonCancelBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonExitAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Close?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    App.setRoot("Main Menu");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
