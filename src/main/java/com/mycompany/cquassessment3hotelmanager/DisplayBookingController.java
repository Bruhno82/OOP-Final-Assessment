/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.time.LocalDate;
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
    int index;
    DataSingleton data;
    
    // Sets values
    public void setValues(int index) {
        this.index = index;
        displayedBooking = bList.get(index);
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
        Boolean bookingTest = true;
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
        if(roomTest == false) {
            alarm("No matching room could be found.");            
        }
        // Checking that updated booking won't conflict with other bookings.
        if(roomTest == true) {
            for(Booking booking: bList) {
                if(booking.getBookingID().equals(displayedBooking.getBookingID()) == false) {
                    if(booking.getCheckIn().compareTo(displayedBooking.getCheckIn()) <= 0 && booking.getCheckOut().compareTo(displayedBooking.getCheckIn()) > 0 ||
                            booking.getCheckIn().compareTo(displayedBooking.getCheckOut()) < 0 && displayedBooking.getCheckOut().compareTo(booking.getCheckOut()) <= 0 ||
                            booking.getCheckIn().compareTo(displayedBooking.getCheckIn()) <= 0 && booking.getCheckOut().compareTo(displayedBooking.getCheckOut()) >= 0) {
                        bookingTest = false;
                        alarm("Room assignment conflicts with existing booking " + booking.getBookingID() + ".");
                        break;
                    }
                }
            }
        }
        if(roomTest == false || bookingTest == false) {
            alarm("Room ID could not be updated.");
        }
        else {
            displayedBooking.setRoomID(rID);
            displayedBooking.setCharges(dailyRate * displayedBooking.getCheckOut().compareTo(displayedBooking.getCheckIn()));
            displayArea.setText(displayedBooking.toString());            
        }
    }
    
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
            start = LocalDate.parse(startDate.getValue().toString());
        }
        // Check date is not before today
        LocalDate today = LocalDate.now();
        if (start.isBefore(today)) {
            alarm("Start date can not be before today.");
            return;
        }
        for (Booking booking: bList) {
            if(booking.getBookingID().equals(displayedBooking.getBookingID()) == false) {
                if(booking.getCheckIn().compareTo(start) <= 0 && booking.getCheckOut().compareTo(start) > 0 ||
                   booking.getCheckIn().compareTo(start) <= 0 && booking.getCheckOut().compareTo(displayedBooking.getCheckOut()) >= 0) { // Start of stay can happen on final day of prior booking, but not before the end AND after its start
                alarm("Start date conflicts with existing booking " + booking.getBookingID() + ".");
                return;
                }
            }
        }
        // Testing if dates are correct.
        if(displayedBooking.getCheckOut().compareTo(start) <= 0) {
            alarm("End of stay cannot occur before beginning of stay.\n"
                    + "Booking cannot start and end on the same day.");
            return;
        }        
        // Checking if room exists.
        for(StandardRoom room: rList) {
            if(rID.equals(room.getRoomID())) {
                dailyRate = room.getDailyRate();
                break;
            } 
        }           
        // If everything is fine, alter the start date.
        days = displayedBooking.getCheckOut().compareTo(start);
        displayedBooking.setCheckIn(start);
        displayedBooking.setCharges(days * dailyRate);        
        displayArea.setText(displayedBooking.toString());
    }
    
    public void updateEnd() {
        LocalDate end;
        Boolean bookingTest = true;
        if (endDate.getValue() == null) {
            alarm("Dates are not valid.");
            return;
        } else {
            end = LocalDate.parse(displayedBooking.toString());
        }
        for (Booking booking: bList) {
            if(booking.getBookingID().equals(displayedBooking.getBookingID()) == false) {
                if(booking.getCheckIn().compareTo(end) < 0 && end.compareTo(booking.getCheckOut()) <= 0 ||
                   booking.getCheckIn().compareTo(displayedBooking.getCheckIn()) <= 0 && booking.getCheckOut().compareTo(end) >= 0) { // Start of stay can happen on final day of prior booking, but not before the end AND after its start
                bookingTest = false;
                alarm("End date conflicts with existing booking " + booking.getBookingID() + ".");
                break;
                }
            }
        }
        if(bookingTest == true) {
            displayedBooking.setCheckOut(end);
            displayArea.setText(displayedBooking.toString());
        }
        else {
            alarm("End date could not be altered.");
        }
    }
    
    public void deleteBooking() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText("Are you sure you want to cancel " + bList.get(index).getBookingID() + "? This will also close the window.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmDialog.showAndWait();

        // Check if the user clicked the OK button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the exit, delete Booking and close the current window
            bList.remove(index);
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
