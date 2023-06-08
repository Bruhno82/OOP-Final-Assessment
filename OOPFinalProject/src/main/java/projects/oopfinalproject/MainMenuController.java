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

import javafx.scene.control.Button;

public class MainMenuController implements Initializable {


    @FXML
    private Button buttonBookings;
    @FXML
    private Button buttonClientInformation;
    @FXML
    private Button buttonInvoices;
    @FXML
    private Button buttonCheck;
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
    private void handleButtonBookings(ActionEvent event) throws IOException {
        App.setRoot("Booking");
    }

    @FXML
    private void handleButtonClientInformation(ActionEvent event) throws IOException {
        App.setRoot("Client");
    }

    @FXML
    private void handleButtonInvoices(ActionEvent event) throws IOException {
        App.setRoot("Invoices");
    }

    @FXML
    private void handleButtonCheck(ActionEvent event) throws IOException {
        App.setRoot("Check");
    }

    @FXML
    private void handleButtonExitAction(ActionEvent event) {
    }

}
