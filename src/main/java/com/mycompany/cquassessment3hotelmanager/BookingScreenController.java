/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import java.time.LocalDate;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
        }else {
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
        }
        // Testing if room exists.
        for(StandardRoom room: rList) {
            if(rID.equals(room.getRoomID())) {
                roomTest = true;
                pID = "P" + rList.indexOf(room);
                cost = room.getDailyRate() * end.compareTo(start);
                break;
            }
        }
        if(roomTest == false) {
            alarm("Room could not be found.");
        }
        // Testing if the booking will overlap with other bookings in the same room.
        if(roomTest == true) {
            for(Booking booking: bList) {
                if(booking.getRoomID().equals(rID)) {
                    if(booking.getCheckIn().compareTo(start) <= 0 && booking.getCheckOut().compareTo(start) > 0) { // Start of stay can happen on final day of prior booking, but not before the end AND after its start
                        bookingTest = false;
                        alarm("Start date conflicts with existing booking " + booking.getBookingID() + ".");
                        break;
                    }
                    if(booking.getCheckIn().compareTo(end) < 0 && end.compareTo(booking.getCheckOut()) <= 0) {// End of stay can happen on first day of another booking, but not after start date AND before end date
                        alarm("End date conflicts with existing booking " + booking.getBookingID() + ".");                      
                        bookingTest = false;
                        break;
                    }
                    if(booking.getCheckIn().compareTo(start) <= 0 && booking.getCheckOut().compareTo(end) >= 0) { // Start of stay cannot begin before a prior booking's start if end of stay occurs after prior booking's end
                        bookingTest = false;
                        alarm("Proposed dates overlap existing booking " + booking.getBookingID() + ".");
                        break;
                    }
                }
            }
        }

        // Testing if dates are correct.
        if(end.compareTo(start) <= 0) {
            dateTest = false;
            alarm("End of stay cannot occur before beginning of stay.\n"
                    + "Booking cannot start and end on the same day.");
        }
        else {
            dateTest = true;
        }
        // Create Booking.
        if(clientTest == false || roomTest == false || bookingTest == false || dateTest == false) {
            alarm("Booking could not be created.");
        }
        else {
            
            bList.add(new Booking("B" + b, custIDField.getText(),
            roomIDField.getText(), pID, start, end, cost));

            bookingDisplay.setText("Room " + rID + " booked. (ID:B" + b + ")");            
            data.setBookingCounter(b + 1);
            data.setBookingList(bList);
        }
    }
    
    @FXML
    public void createClient() {
        
        // Check fields are filled
        if (nameField.getText().isBlank() || emailField.getText().isBlank() || phoneField.getText().isBlank() || regoField.getText().isBlank()) {
            alarm("All fields must be filled");
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
    private void exitButton(ActionEvent event) {
        Stage thisStage = (Stage) exitBtn.getScene().getWindow();
        thisStage.close();
    }
}
