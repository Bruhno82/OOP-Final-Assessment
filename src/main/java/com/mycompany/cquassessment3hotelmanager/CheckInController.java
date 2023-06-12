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
    private Button checkInBtn;

    @FXML
    private Label confirmDisplay;

    @FXML
    private TextField customerField;

    @FXML
    private Label customerLabel;

    @FXML
    private TextField dateField;

    @FXML
    private Label dateLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField roomField;

    @FXML
    private Label roomLabel;

    @FXML
    private Button searchBtn;
    
    String bID;
    String cID = "";
    String rID;
    LocalDate date;
    boolean bookingTest = false; 
    Boolean roomTest = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        bList = data.getBookingList();
        cList = data.getClientList();
        rList = data.getRoomList();
        pList = data.getParkList();
        
        // Hide fields
        hideFields();
        

    }    
    
    DataSingleton data;
    
    ArrayList<Booking> bList;
    ArrayList<Client> cList;
    ArrayList<StandardRoom> rList;
    ArrayList<Carpark> pList;
    
    
    public void hideFields() {
        roomLabel.setVisible(false);
        customerLabel.setVisible(false);
        dateLabel.setVisible(false);
        roomField.setVisible(false);
        customerField.setVisible(false);
        dateField.setVisible(false);
        checkInBtn.setVisible(false);
    }
    
    public void showFields() {
        roomLabel.setVisible(true);
        customerLabel.setVisible(true);
        dateLabel.setVisible(true);
        roomField.setVisible(true);
        customerField.setVisible(true);
        dateField.setVisible(true);
        checkInBtn.setVisible(true);
    }
    
    @FXML
    public void searchBooking() throws IOException {
        // Check for blank fields
        if (bookingField.getText().isBlank()) {
            alarm("Enter a booking ID.");
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
                customerField.setText(cID);
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
    public void checkIn() throws IOException {
        // Check for early check in
        if (LocalDate.now().isBefore(date) || LocalDate.now().isEqual(date)) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirmation");
            confirmDialog.setHeaderText("Do you want to check this customer in early?");

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmDialog.showAndWait();

            // Check if the user clicked the OK button
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Check if room is occupied
                for (StandardRoom room : rList) {
                    if (rID.equals(room.getRoomID())) {
                        if (room.getOccupied()) {
                            alarm("This room is currently occupied.");
                            return;
                        } else {
                            for (Booking booking : bList) {
                                if (bID.equals(booking.getBookingID())) {
                                    int bIndex = bList.indexOf(booking);
                                    booking.setCheckIn(LocalDate.now());
                                    room.setOccupied(true);
                                    
                                    // Reserve car park
                                    String regoNo = " ";
                                    String carParkID = booking.getParkID();
                                    for (Carpark carpark : pList) {
                                        if (carParkID.equals(carpark.getParkID())) {
                                            carpark.setOccupied(true);
                                            for (Client client : cList) {
                                                if (client.getClientID().equals(cID)) {
                                                    regoNo = client.getRegoNo();
                                                    break; // Exit the loop after finding the client's registration number
                                                }
                                            }
                                            carpark.setRegoNo(regoNo);
                                            break; // Exit the loop after finding the corresponding car park
                                        }
                                    }
                                    
                                    success("Check-in success.");
                                    // Close the window
                                    Stage currentStage = (Stage) exitBtn.getScene().getWindow();
                                    currentStage.close();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Room not found
        alarm("Room not found.");
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
