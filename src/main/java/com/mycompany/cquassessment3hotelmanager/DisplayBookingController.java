/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private DatePicker startField;
    @FXML
    private DatePicker endField;
    @FXML
    private Button updateBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label confirmDisplay;
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
    int index;
    DataSingleton data;
    
    // Sets values
    public void setValues(int index) {
        this.index = index;
        this.displayArea.setText(bList.get(index).toString());
    }
    
    public void updateBooking() {
        
    }        
    
    public void deleteBooking() {
        
    }
    
    // Provides alert functionality.
    private void alarm(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                message);
        alert.showAndWait();
    }
    
    public void exitButton() {
        
    }
}
