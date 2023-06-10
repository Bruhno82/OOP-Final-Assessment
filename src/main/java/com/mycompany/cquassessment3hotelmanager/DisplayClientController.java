/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class DisplayClientController implements Initializable {


    @FXML
    private TextArea displayArea;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button updateBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField regoField;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label confirmDisplay;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
        
        cList = data.getClientList();
    }   
    
    ArrayList<Client> cList;
    //ArrayList<Booking> bList; We should check if the client has any bookings and remove them in the event of deletion. Maybe discuss this first, check the pros and cons?
    
    int index;
    DataSingleton data;
    
    // Sets values
    public void setValues(int index) {
        this.index = index;
        this.displayArea.setText(cList.get(index).toString());
    }  
}
