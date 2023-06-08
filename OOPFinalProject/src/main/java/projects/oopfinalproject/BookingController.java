/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projects.oopfinalproject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BookingController {

    @FXML
    private Button buttonCancelBooking;

    @FXML
    private Button buttonCreateBooking;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonSearchBooking;

    @FXML
    private Button buttonUpdateBooker;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleButtonUpdateBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonSearchBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonCreateBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonCancelBooking(ActionEvent event) {
    }

    @FXML
    private void handleButtonExitAction(ActionEvent event) {
    }

}
