/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class CheckOutController implements Initializable {

    @FXML
    private TextField bookingField;

    @FXML
    private Button checkOutBtn;

    @FXML
    private Label checkOutDisplay;

    @FXML
    private TextField clientField;

    @FXML
    private Label clientLabel;

    @FXML
    private TextField dateField;

    @FXML
    private Label dateLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private TextArea invoiceDisplay;

    @FXML
    private TextField roomField;

    @FXML
    private Label roomLabel;

    @FXML
    private Button searchBtn;
    
    String bID;
    String cID;
    String rID;
    double rate;
    double totalRate;
    int days;
    double charges;
    double total;
    LocalDate date;
    LocalDate today = LocalDate.now();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        bList = data.getBookingList();
        cList = data.getClientList();
        rList = data.getRoomList();
        
        hideFields();
    }    
    
    DataSingleton data;
    
    ArrayList<Booking> bList;
    ArrayList<Client> cList;
    ArrayList<StandardRoom> rList;
    
    
    
    public void hideFields() {
        clientLabel.setVisible(false);
        roomLabel.setVisible(false);
        dateLabel.setVisible(false);
        clientField.setVisible(false);
        roomField.setVisible(false);
        dateField.setVisible(false);
        checkOutBtn.setVisible(false);
    }

    public void showFields() {
        clientLabel.setVisible(true);
        roomLabel.setVisible(true);
        dateLabel.setVisible(true);
        clientField.setVisible(true);
        roomField.setVisible(true);
        dateField.setVisible(true);
        checkOutBtn.setVisible(true);
    }
    
    @FXML void searchBooking() throws IOException {
        // Check for blank entry
        if (bookingField.getText().isBlank()) {
            alarm("Booking ID can not be blank.");
            return;
        } else {
            bID = bookingField.getText();
            boolean bookingFound = false; // Flag to indicate if a booking is found
        
        
            // Check if booking exists
            for (Booking booking : bList) {
                if (bID.equals(booking.getBookingID())) {
                    // Get Client ID
                    cID = booking.getClientID();
                    // Get room ID
                    rID = booking.getRoomID();
                    // Get check-in date
                    date = booking.getCheckIn();
                    bookingFound = true;
                    break; // Exit the loop once a booking is found
                }
            }
            
            if (bookingFound) {
                // Fill fields
                clientField.setText(cID);
                roomField.setText(rID);
                dateField.setText(date != null ? date.toString() : "");

                // Display fields
                showFields();
            } else {
                alarm("No booking found.");
                // Hide fields
                hideFields();
            }
        }
    }
    
    @FXML
    public void checkOut() throws IOException {
        // check room is occupied
        for (StandardRoom room : rList) {
            if (rID.equals(room.getRoomID())) {
                if (room.getOccupied().equals(false)) {
                    alarm("The room is not occupied.");
                    return;
                }
            }
        }
        
        // Work out cost of stay
        // Calculate days
        days = (int) ChronoUnit.DAYS.between(date, today);
        // Get room rate
        for (StandardRoom room : rList) {
            if (rID.equals(room.getRoomID())) {
                rate = room.getDailyRate();
            }  
        }
        totalRate = days * rate;
        
        // Add services
        total = totalRate + charges;
        
        // Change booking
        for (Booking booking : bList) {
            if (bID.equals(booking.getBookingID())) {
                booking.setCheckOut(today);
                booking.setCharges(total);
            }
        }
        
        // Display invoice
        String invDisplay = String.format("Booking No: %s\nRoom No: %s\nCheck In date: %s\nCheck Out date: %s\nDaily Rate: %s\n"
                + "Charges: $%s\nTotal $%s"
                ,bID, rID, date, today, rate, charges, total);
        invoiceDisplay.setText(invDisplay);
        
        // Set room as unoccupied
        for (StandardRoom room : rList) {
            if (rID.equals(room.getRoomID())) {
                room.setOccupied(false);
            }
        }
        success("Check out success.");
    }
    
    @FXML
    private void handleExit() {
        // Create a confirmation dialog
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText("Are you sure you want to exit?");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmDialog.showAndWait();

        // Check if the user clicked the OK button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the exit, close the current window
            Stage currentStage = (Stage) exitBtn.getScene().getWindow();
            currentStage.close();
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
