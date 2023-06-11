/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class BookingScreenController implements Initializable {


    @FXML
    private TextField custIDField;
    @FXML
    private TextField roomIDField;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button bookRoomBtn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField regoField;
    @FXML
    private Button createClientBtn;
    @FXML
    private Label bookingDisplay;
    @FXML
    private Label clientDisplay;
    @FXML
    private Button exitBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        rList = data.getRoomList();
        bList = data.getBookingList();
        cList = data.getClientList();
    }    
    
    DataSingleton data;
    
    ArrayList<StandardRoom> rList;
    ArrayList<Booking> bList;
    ArrayList<Client> cList;
    
    @FXML
    public void createBooking() {
        int b = data.getBookingCounter();
        
        String cID;
        String rID;
        String pID;
        LocalDate start;
        LocalDate end;
        Double cost;
        
        // Check customerID and roomID is not blank
        if (custIDField.getText().isBlank() || roomIDField.getText().isBlank()) {
            alarm("ID fields can not be blank.");
            return;
        }
        else {
            cID = custIDField.getText();
            rID = roomIDField.getText();
            pID = "";
            cost = 0.0;
        }
        
        // Check dates are valid
        if (startDate.getValue() == null || endDate.getValue() == null) {
            alarm("Dates are not valid.");
            return;
        } else {
            start = LocalDate.parse(startDate.getValue().toString());
            end = LocalDate.parse(endDate.getValue().toString());
        }
        
        // Check date is not before today
        LocalDate today = LocalDate.now();
        if (start.isBefore(today)) {
            alarm("Start date can not be before today.");
            return;
        }

        boolean clientTest = false;
        boolean roomTest = false;
        boolean bookingTest = true; // True by default so we don't have to test for an empty array.
        boolean dateTest = false;
        
        // Testing if Client exists.
        for(Client client: cList) {
            if(cID.equals(client.getClientID())) {
                clientTest = true;
                break;
            } 
        }
        // Displays alert if client ID cannot be found. This wouldn't work in an Else statement for some reason.
        if (clientTest == false) {
            alarm("Client could not be found.");
            return;
        }
        // Testing if room exists.
        for(StandardRoom room: rList) {
            if(rID.equals(room.getRoomID())) {
                roomTest = true;
                pID = "P" + rList.indexOf(room);
                cost = room.getDailyRate() * ChronoUnit.DAYS.between(start, end);
                break;
            }
        }
        if(roomTest == false) {
            alarm("Room could not be found.");
            return;
        }
        // Testing if the booking will overlap with other bookings in the same room.
        for(Booking booking: bList) {          
            if(booking.getRoomID().equals(rID)) {

                if(ChronoUnit.DAYS.between(booking.getCheckIn(), start) >= 0 && ChronoUnit.DAYS.between(booking.getCheckOut(), start) < 0) { // Start of stay can happen on final day of prior booking, but not before the end AND after its start
                    alarm("Start date conflicts with existing booking " + booking.getBookingID() + ".");
                    return;
                }
                if(ChronoUnit.DAYS.between(booking.getCheckIn(), end) > 0 && ChronoUnit.DAYS.between(booking.getCheckOut(), end) <= 0) {// End of stay can happen on first day of another booking, but not after start date AND before end date
                    alarm("End date conflicts with existing booking " + booking.getBookingID() + ".");                      
                    return;
                }
                if(ChronoUnit.DAYS.between(start, booking.getCheckIn()) > 0 && ChronoUnit.DAYS.between(end, booking.getCheckOut()) < 0) { // Start of stay cannot begin before a prior booking's start if end of stay occurs after prior booking's end
                    alarm("Proposed dates overlap existing booking " + booking.getBookingID() + ".");
                    return;
                }
            }
        }

        // Testing if dates are correct.
        if(end.isBefore(start)) {
            alarm("End of stay cannot occur before beginning of stay.\n"
                    + "Booking cannot start and end on the same day.");
            return;
        }
        // Since return has not been called, create Booking.            
        bList.add(new Booking("B" + b, custIDField.getText(),
        roomIDField.getText(), pID, start, end, cost));

        bookingDisplay.setText("Room " + rID + " booked. (ID:B" + b + ")");            
        data.setBookingCounter(b + 1);
        data.setBookingList(bList);
        }
    
    @FXML
    public void createClient() {
        
        // Check fields are filled
        if (nameField.getText().isBlank() || nameField.getText().contains(",") || 
                emailField.getText().isBlank() || emailField.getText().contains(",") ||
                phoneField.getText().isBlank() || phoneField.getText().contains(",") ||
                regoField.getText().isBlank() || regoField.getText().contains(",")) {
            alarm("All fields must be filled.\nNo fields can contain commas.");
            return;
        }
        
        int c = data.getClientCounter();
        cList.add(new Client("C" + c, nameField.getText(), emailField.getText(),
                phoneField.getText(), regoField.getText()));
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        regoField.setText("");
        
        clientDisplay.setText("Client C" + c + " created.");
        data.setClientCounter(c + 1);
        data.setClientList(cList);        
    }
    
    // Provides alert functionality.
    private void alarm(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                message);
        alert.showAndWait();
    }
   
    @FXML
    private void exitButton() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText("Are you sure you want to close this window?");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmDialog.showAndWait();

        // Check if the user clicked the OK button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the exit, close the current window
            Stage currentStage = (Stage) exitBtn.getScene().getWindow();
            currentStage.close();        
        }
    }
}
