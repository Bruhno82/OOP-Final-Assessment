/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private Label title;
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
    
    Client client;
    DataSingleton data;
    
    // Sets values
    public void setValues(Client client) {
        this.client = client;
        this.displayArea.setText(client.toString());
        this.title.setText("Displaying Client " + client.getClientID());
    }  
    
    // Updates Name field
    public void updateName() {
        if (nameField.getText().isBlank()) {
            alarm("Name field can not be blank.");
            return;
        }
        if (nameField.getText().contains(",")) {
            alarm("Name field cannot contain a comma.");
        }
        client.setClientName(nameField.getText());
        displayArea.setText(client.toString());
    }
    
    // Updates Email field
    public void updateEmail() {
        if (emailField.getText().isBlank()) {
            alarm("email field can not be blank.");
            return;
        }
        if (emailField.getText().contains(",")) {
            alarm("Email field cannot contain a comma.");
        }
        client.setEmail(emailField.getText());
        displayArea.setText(client.toString());        
    }
    
    public void updatePhone() {
        if (phoneField.getText().isBlank()) {
            alarm("Phone field can not be blank.");
            return;
        }
        if (phoneField.getText().contains(",")) {
            alarm("Phone field cannot contain a comma.");
        }
        client.setPhoneNo(phoneField.getText());
        displayArea.setText(client.toString());        
    }
    
    public void updateRego() {
        if (regoField.getText().isBlank()) {
            alarm("Rego field can not be blank.");
            return;
        }
        if (regoField.getText().contains(",")) {
            alarm("Rego field cannot contain a comma.");
        }
        client.setRegoNo(regoField.getText());
        displayArea.setText(client.toString());        
    }
    
    // Deletes Client entry
    public void deleteClient() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText("Are you sure you want to cancel " + client.getClientID() + "? This will also close the window.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmDialog.showAndWait();

        // Check if the user clicked the OK button
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the exit, delete Booking and close the current window
            client.setClientID("0");
            client.setClientName("Deleted");
            client.setEmail("");
            client.setPhoneNo("");
            client.setRegoNo("");
            Stage currentStage = (Stage) exitBtn.getScene().getWindow();
            currentStage.close();        
        }        
    }    
    
    // Closes Window
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
    
    // Provides alert functionality.
    private void alarm(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                message);
        alert.showAndWait();
    }
}
