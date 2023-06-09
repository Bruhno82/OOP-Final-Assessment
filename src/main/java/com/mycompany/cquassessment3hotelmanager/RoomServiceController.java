/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author chris
 */
public class RoomServiceController implements Initializable {


    @FXML
    private TextField bookingField;
    @FXML
    private TextField serviceField;
    @FXML
    private TextField costField;
    @FXML
    private Button exitBtn;
    @FXML
    private Button addServiceBtn;
    @FXML
    private Label confirmDisplay;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        
        bList = data.getBookingList();

    }    
    
    DataSingleton data;
    
    ArrayList<Booking> bList;
    
    @FXML
    public void saveService() throws IOException {
        // Check fields are not blank
        
        // Check booking exists
        
        // Add charges
    }
}
