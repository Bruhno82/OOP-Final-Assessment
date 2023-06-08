/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projects.oopfinalproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXMLCheckController implements Initializable {


    @FXML
    private Button buttonCheckIn;

    @FXML
    private Button buttonCheckOut;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonSearchBooking;

    @FXML
    private TextField txtBookingId;
    
    @FXML
    private TextArea txtDisplayBooking;
    
    int bookingId;
    LocalDate checkIn;
    LocalDate checkOut;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonCheckIn.setVisible(false);
        buttonCheckOut.setVisible(false);
    }    
    
    @FXML
    private void handleButtonCheckIn(ActionEvent event) throws IOException {
        bookingId = Integer.parseInt(txtBookingId.getText());
        checkIn = LocalDate.now();
        for (Booking b : App.getBookingData().getBookings()) {
            if (bookingId == b.getBookingId()) {
                b.setCheckIn(checkIn);
            }
        }
        JOptionPane.showMessageDialog(null, "Client checked in", "Success", JOptionPane.INFORMATION_MESSAGE);
        App.setRoot("Main Menu");
    }

    @FXML
    private void handleButtonSearchBooking(ActionEvent event) {
        if (txtBookingId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please enter a booking ID", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
        }
        boolean bookingFound = false;
        bookingId = Integer.parseInt(txtBookingId.getText());
        for (Booking b : App.getBookingData().getBookings()) {
            if (bookingId == b.getBookingId()) {
                bookingFound = true;
                break;
            }
        }

        if (bookingFound) {
            StringBuilder displayString = new StringBuilder();
            for (Booking booking : App.getBookingData().getBookings()) {
                displayString.append(booking.displayString()).append("\n");
            }
            txtDisplayBooking.setText(displayString.toString());
            buttonCheckIn.setVisible(true);
            buttonCheckOut.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No booking found", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void handleButtonCheckOut(ActionEvent event) throws IOException {
        bookingId = Integer.parseInt(txtBookingId.getText());
        checkOut = LocalDate.now();
        for (Booking b : App.getBookingData().getBookings()) {
            if (bookingId == b.getBookingId()) {
                b.setCheckOut(checkOut);
            }
        }
        JOptionPane.showMessageDialog(null, "Client checked Out", "Success", JOptionPane.INFORMATION_MESSAGE);
        App.setRoot("Main Menu");
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
