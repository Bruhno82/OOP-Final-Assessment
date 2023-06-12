/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class DisplayBookingController implements Initializable {


    @FXML
    private TextArea displayArea;
    @FXML
    private TextField clientField;
    @FXML
    private TextField roomField;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button updateCIDBtn;
    @FXML
    private Button updateRIDBtn;
    @FXML
    private Button updateStartBtn;
    @FXML
    private Button updateEndBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label title;
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
    
    ArrayList<StandardRoom> rList;
    ArrayList<Booking> bList;
    ArrayList<Client> cList;
    Booking displayedBooking;
    DataSingleton data;
    
    // Sets values
    public void setValues(Booking displayedBooking) {
        this.displayedBooking = displayedBooking;
        this.displayArea.setText(displayedBooking.toString());
        this.title.setText("Displaying Booking " + displayedBooking.getBookingID());
    }
    
    public void updateCID() {
        String cID;
        Boolean clientTest = false;
        if (clientField.getText().isBlank()) {
            alarm("ID fields can not be blank.");
            return;
        }
        else {
            cID = clientField.getText();
        }
        for(Client client: cList) {
            if(cID.equals(client.getClientID())) {
                clientTest = true;
                break;
            } 
        }       
        if(clientTest == false) {
            alarm("No matching client could be found.");            
        }
        if(clientTest == true) {
            displayedBooking.setClientID(cID);
            displayArea.setText(displayedBooking.toString());
            roomField.setText("");
        }
        else {
            alarm("Client ID could not be updated.");
        }
    }        
    
    public void updateRID() {
        String rID;
        double dailyRate = 0.0;
        Boolean roomTest = false;
        if (roomField.getText().isBlank()) {
            alarm("Room field can not be blank.");
            return;
        }
        else {
            rID = roomField.getText();
        }
        // Checking if room exists.
        for(StandardRoom room: rList) {
            if(rID.equals(room.getRoomID())) {
                roomTest = true;
                dailyRate = room.getDailyRate();
                break;
            } 
        }       
        // Alarm if test is false. Can't put it in the loop without it repeatedly going off or misfiring.
        if(roomTest == false) {
            alarm("No matching room could be found.");
            return;
        }
        
        // Checking that updated booking won't conflict with other bookings.
        if(roomTest == true) {
            for(Booking booking: bList) {
                if(booking.getBookingID().equals(displayedBooking.getBookingID()) == false) {
                    if(booking.getRoomID().equals(displayedBooking.getRoomID())) {
                        LocalDate displayedStart = displayedBooking.getCheckIn();
                        LocalDate displayedEnd = displayedBooking.getCheckOut();
                        LocalDate bookedStart = booking.getCheckIn();
                        LocalDate bookedEnd = booking.getCheckOut();
                    
                        // Check to make sure the booking doesn't conflict with an existing booking. Explanation of code is in BookingScreenController.
                        if(ChronoUnit.DAYS.between(bookedStart, displayedStart) >= 0 && ChronoUnit.DAYS.between(displayedStart, bookedEnd) < 0  ||
                                ChronoUnit.DAYS.between(bookedStart, displayedEnd) > 0 && ChronoUnit.DAYS.between(bookedEnd, displayedEnd) <= 0 ||
                                ChronoUnit.DAYS.between(displayedStart, bookedStart) > 0 && ChronoUnit.DAYS.between(displayedEnd, bookedEnd) < 0) {
                            alarm("Room assignment conflicts with existing booking " + booking.getBookingID() + ".");
                            return;
                        }
                    }
                }
            }
        }
        
        // If return hasn't been called, reassign room ID.
        displayedBooking.setRoomID(rID);
        displayedBooking.setCharges(dailyRate * ChronoUnit.DAYS.between(displayedBooking.getCheckIn(), displayedBooking.getCheckOut()));
        displayArea.setText(displayedBooking.toString());            
    }
    
    // Updates start date and checks for conflicts.
        public void updateStart() {
        LocalDate start;
        double dailyRate = 0.0;
        int days;
        String rID = displayedBooking.getRoomID();

        // Check dates are valid
        if (startDate.getValue() == null) {
            alarm("Dates are not valid.");
            return;
        } else {
            start = startDate.getValue();
        }

        // Check date is not before today
        LocalDate today = LocalDate.now();
        if (start.isBefore(today)) {
            alarm("Start date cannot be before today.");
            return;
        }

        // Checking if room exists
        boolean roomExists = false;
        for (StandardRoom room : rList) {
            if (rID.equals(room.getRoomID())) {
                roomExists = true;
                dailyRate = room.getDailyRate();
                break;
            }
        }

        if (!roomExists) {
            alarm("No matching room could be found.");
            return;
        }

        // Checking for conflicts with other bookings
        for (Booking booking : bList) {
            if (!booking.getBookingID().equals(displayedBooking.getBookingID()) && booking.getRoomID().equals(rID)) {
                LocalDate bookedStart = booking.getCheckIn();
                LocalDate bookedEnd = booking.getCheckOut();

                if (start.isBefore(bookedEnd) && start.plusDays(1).isAfter(bookedStart)) {
                    alarm("Start date conflicts with existing booking " + booking.getBookingID() + ".");
                    return;
                }
            }
        }

        // Updating the start date and charges for the displayed booking
        days = (int) ChronoUnit.DAYS.between(displayedBooking.getCheckIn(), displayedBooking.getCheckOut());
        displayedBooking.setCheckIn(start);
        displayedBooking.setCharges(days * dailyRate);
        displayArea.setText(displayedBooking.toString());
    }
    
    // Updates end date and checks for conflicts.
    public void updateEnd() {
        LocalDate end;
        boolean bookingTest = true;

        if (endDate.getValue() == null) {
            alarm("Dates are not valid.");
            return;
        } else {
            end = endDate.getValue();
        }

        for (Booking booking : bList) {
            if (!booking.getBookingID().equals(displayedBooking.getBookingID()) && booking.getRoomID().equals(displayedBooking.getRoomID())) {
                LocalDate bookedStart = booking.getCheckIn();
                LocalDate bookedEnd = booking.getCheckOut();

                if (end.isAfter(bookedStart) && end.isBefore(bookedEnd)) {
                    bookingTest = false;
                    alarm("End date conflicts with existing booking " + booking.getBookingID() + ".");
                    break;
                }
            }
        }

        if (bookingTest) {
            displayedBooking.setCheckOut(end);
            displayArea.setText(displayedBooking.toString());
        } else {
            alarm("End date could not be altered.");
        }
    }
    
    public void deleteBooking() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText("Are you sure you want to cancel " + displayedBooking.getBookingID() + "? This will also close the window.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmDialog.showAndWait();

        // Check if the user clicked the OK button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the exit, delete Booking and close the current window
            displayedBooking.setBookingID("0");
            displayedBooking.setCharges(0);
            displayedBooking.setCheckIn(null);
            displayedBooking.setCheckOut(null);
            displayedBooking.setClientID("");
            displayedBooking.setParkID("");
            displayedBooking.setRoomID("");
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
    
    public void exitButton() {
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