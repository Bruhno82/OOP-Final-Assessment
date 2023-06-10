/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class CheckInController implements Initializable {


    @FXML
    private TextField bookingField;
    @FXML
    private TextField customerField;
    @FXML
    private Button checkInBtn;
    @FXML
    private Label confirmDisplay;
    @FXML
    private Button exitBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        bList = data.getBookingList();
        cList = data.getClientList();
        rList = data.getRoomList();
    }    
    
    DataSingleton data;
    
    ArrayList<Booking> bList;
    ArrayList<Client> cList;
    ArrayList<StandardRoom> rList;
    
    @FXML
    public void checkIn() throws IOException {
        
        
        String bID;
        String cID;
        boolean bookingTest = false; 
        Boolean clientTest = false;
        
        
        // Check for blank fields
        if (bookingField.getText().isBlank() || customerField.getText().isBlank()) {
            alarm("All fields must be filled.");
            return;
        } else {
            bID = bookingField.getText();
            cID = customerField.getText();
        }
        
        // Check if booking exists
        for (Booking booking : bList) {
            if (bID.equals(booking.getBookingID())) {
                if (booking.getCheckIn().isAfter(LocalDate.now())) {
                    alarm("Booking date is after today.");
                    return;
                }
                bookingTest = true;
                // Get the roomID from the booking
                String roomID = booking.getRoomID();
                // Check if the room is occupied
                for (StandardRoom room : rList) {
                    if (room.getRoomID().equals(roomID) && room.getOccupied()) {
                        alarm("Room is already occupied.");
                        return;
                    }
                }
                break;
            }
        }
        
        if (bookingTest == false) {
            alarm("No booking exists.");
            return;
        }
        
        // Check if client exists
        for(Client client: cList) {
            if(cID.equals(client.getClientID())) {
                clientTest = true;
                break;
            } 
        }
        
        if (clientTest == false) {
            alarm("No client exists.");
            return;
        }
        
        // Modify booking
        if (bookingTest && clientTest) {
            for (Booking booking : bList) {
                if (bID.equals(booking.getBookingID())) {
                    booking.setCheckIn(LocalDate.now());
                }
            
                String rID = booking.getRoomID();
                for (StandardRoom room : rList) {
                    if (rID.equals(room.getRoomID())) {
                        room.setOccupied(true);
                    }
                }
                break;
            }   
        }
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
}
