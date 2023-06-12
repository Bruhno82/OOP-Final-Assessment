/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author chris
 */
public class RoomServiceController implements Initializable {



    @FXML
    private Button addServiceBtn;

    @FXML
    private TextField bookingField;

    @FXML
    private Label confirmDisplay;

    @FXML
    private TextField costField;

    @FXML
    private Label costLabel;

    @FXML
    private TextField customerField;

    @FXML
    private Label customerLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField roomField;

    @FXML
    private Label roomLabel;

    @FXML
    private Button searchBut;

    @FXML
    private TextField serviceField;

    @FXML
    private Label serviceLabel;
    
    DataSingleton data = DataSingleton.getData();
    
    ArrayList<Booking> bList = data.getBookingList();
    String bID;
    String cID;
    String rID;
    String sType;
    double sCost;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideFields();
    }    
    
    
    
    public void hideFields() {
        customerLabel.setVisible(false);
        roomLabel.setVisible(false);
        serviceLabel.setVisible(false);
        costLabel.setVisible(false);
        customerField.setVisible(false);
        roomField.setVisible(false);
        serviceField.setVisible(false);
        costField.setVisible(false);
        addServiceBtn.setVisible(false);
    }
    
    public void showFields() {
        customerLabel.setVisible(true);
        roomLabel.setVisible(true);
        serviceLabel.setVisible(true);
        costLabel.setVisible(true);
        customerField.setVisible(true);
        roomField.setVisible(true);
        serviceField.setVisible(true);
        costField.setVisible(true);
        addServiceBtn.setVisible(true);
    }
    
    @FXML
    public void searchBooking() throws IOException {
        // Check fields are not blank
        if (bookingField.getText().isBlank()) {
            alarm("Enter a booking ID.");
            return;
        } else {
            bID = bookingField.getText();
        }
        
        // Check booking exists
        Boolean bookingFound = false;
        for (Booking booking : bList) {
            if (bID.equals(booking.getBookingID())) {
                cID = booking.getClientID();
                rID = booking.getRoomID();
                bookingFound = true;
            }
        }
        
        if (bookingFound) {
            roomField.setText(rID);
            customerField.setText(cID);
            showFields();
        } else {
            alarm("No booking found.");
            hideFields();
        }
    }
    
    
    @FXML
    public void saveService() throws IOException {
        // Check fields are not empty
        if (serviceField.getText().isBlank()) {
            alarm("Enter a service type.");
            return;
        }
        
        // Prevents a rogue comma from bricking LoadRecords
        if(serviceField.getText().contains(",")) {
            alarm("Service cannot contain a comma.");
            return;
        }
        
        if (costField.getText().isBlank()) {
            alarm("Enter a service cost.");
        } else {
            try {
                sType = serviceField.getText();
                sCost = Double.parseDouble(costField.getText());
            } catch (NumberFormatException e) {
                alarm("Wrong fromat. Cost must be a number.");
                return;
            }
        }
        
        for (Booking booking : bList) {
            if (bID.equals(booking.getBookingID())) {
                booking.setCharges(booking.getCharges() + sCost);
                success ("Service added to booking ID " + bID);
                hideFields();
            }
        }
        
    }
    
    // Provides alert functionality.
    private void alarm(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                message);
        alert.showAndWait();
    }
    
    // Show success message
    private void success(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
