/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projects.oopfinalproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXMLBookingController implements Initializable {



    @FXML
    private Button buttonCancelBooking;

    @FXML
    private Button buttonCreateBooking;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonSearchBooking;

    @FXML
    private Button buttonUpdate;

    @FXML
    private DatePicker dateCheckIn;

    @FXML
    private DatePicker dateCheckOut;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtClientId;

    @FXML
    private TextField txtRoomId;
    
    int bookingId;
    int clientId;
    int roomId;
    LocalDate checkIn;
    LocalDate checkOut;
    double charges;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonUpdate.setVisible(false);
        buttonCancelBooking.setVisible(false);
    }    
    
    @FXML
    private void handleButtonUpdateBooking() throws IOException {
        bookingId = Integer.parseInt(txtBookingId.getText());
        
        // delete booking
        Iterator<Booking> iterator = App.getBookingData().getBookings().iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (bookingId == booking.getBookingId()) {
                iterator.remove(); // Remove the booking from the array
                break;
            }
        }
        
        // add modified booking
        handleButtonCreateBooking();
    }

    @FXML
    private void handleButtonSearchBooking(ActionEvent event) {
        if (txtBookingId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Enter a booking number to search", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                bookingId = Integer.parseInt(txtBookingId.getText());
                boolean bookingFound = false;
                for (Booking b : App.getBookingData().getBookings()) {
                    if (bookingId == b.getBookingId()) {
                        bookingFound = true;
                        txtClientId.setText(String.valueOf(b.getClientId()));
                        txtRoomId.setText(String.valueOf(b.getRoomId()));
                        dateCheckIn.setValue(b.getCheckIn());
                        dateCheckOut.setValue(b.getCheckOut());
                        buttonUpdate.setVisible(true);
                        buttonCancelBooking.setVisible(true);
                        break;
                    }
                }
                if (!bookingFound) {
                    JOptionPane.showMessageDialog(null, "No booking number found", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Booking ID must be a number", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    private void handleButtonCreateBooking() throws IOException {
        if (txtBookingId.getText().isBlank() || txtClientId.getText().isBlank() || txtRoomId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (dateCheckIn.getValue() == null || dateCheckOut.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Date is not valid. Please select a date with the date picker", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            bookingId = Integer.parseInt(txtBookingId.getText());
            clientId = Integer.parseInt(txtClientId.getText());
            roomId = Integer.parseInt(txtRoomId.getText());
            checkIn = dateCheckIn.getValue();
            checkOut = dateCheckOut.getValue();
            charges = 0.0;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Booking ID, Client ID and Room ID must be positive numbers", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Check booking ID does not exist
        boolean bookingFound = false;
        for (Booking b : App.getBookingData().getBookings()) {
            if (bookingId == b.getBookingId()) {
                bookingFound = true;
                break;
            }
        }

        if (bookingFound == true) {
            JOptionPane.showMessageDialog(null, "Booking ID already exists", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Check client ID exists
        boolean clientFound = false;
        for (Client c : App.getClientData().getClients()) {
            if (clientId == c.getClientId()) {
                clientFound = true;
                break;
            }
        }

        if (clientFound == false) {
            JOptionPane.showMessageDialog(null, "Client ID does not exist", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Check room ID exists
        boolean roomFound = false;
        for (Room r : App.getRoomData().getRooms()) {
            if (roomId == r.getRoomId()) {
                roomFound = true;
                break;
            }
        }

        if (roomFound == false) {
            JOptionPane.showMessageDialog(null, "Room ID does not exist", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create the booking
        Booking b = new Booking(bookingId, clientId, roomId, checkIn, checkOut, charges);
        App.getBookingData().addBooking(b);
        JOptionPane.showMessageDialog(null, "Booking created", "Success", JOptionPane.INFORMATION_MESSAGE);
        App.setRoot("Main Menu");
    }

    @FXML
    private void handleButtonCancelBooking(ActionEvent event) {
        bookingId = Integer.parseInt(txtBookingId.getText());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this booking?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Iterator<Booking> iterator = App.getBookingData().getBookings().iterator();
                while (iterator.hasNext()) {
                    Booking booking = iterator.next();
                    if (bookingId == booking.getBookingId()) {
                        iterator.remove(); // Remove the booking from the array
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Booking cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                try {
                    App.setRoot("Main Menu");
                } catch (IOException ex) {
                }
            }
        });
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
