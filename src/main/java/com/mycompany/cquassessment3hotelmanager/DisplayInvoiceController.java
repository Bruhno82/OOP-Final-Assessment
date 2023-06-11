/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class DisplayInvoiceController implements Initializable {


    @FXML
    private Label title;
    @FXML
    private TextField totalField;
    @FXML
    private Button updateTotalBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private CheckBox paidBox;
    @FXML
    private TextArea displayArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = DataSingleton.getData();
    }    
    private Invoice invoice;
    
    DataSingleton data;
    
    public void setValues(Invoice invoice) {   
            this.invoice = invoice;
            title.setText("Displaying Invoice " + invoice.getInvoiceNo());
            displayArea.setText(invoice.toString());
            if(invoice.getPaid() == true) {
                paidBox.setSelected(true);
            }
            else {
                paidBox.setSelected(false);
            }
    }
    
    // Updates price
    @FXML
    public void updatePrice() {
        try {
            if(totalField.getText() == null) {
                alarm("Field cannot be empty.");
                return;
            }
            double newPrice = Double.parseDouble(totalField.getText());
            if(newPrice <= 0) {
                alarm("Price cannot be zero or negative.");
                return;
            }
            invoice.setPrice(newPrice);
            displayArea.setText(invoice.toString());
        }
        catch (NumberFormatException e) {
            alarm("Numerical input must be provided.");
            return;
        }
    }
    
    // Updates the Paid status of the invoice
    @FXML
    public void updatePaid() {
        if(paidBox.isSelected()) {
            invoice.setPaid(true);
            displayArea.setText(invoice.toString());
        }
        else {
            invoice.setPaid(false);
            displayArea.setText(invoice.toString());        
        }
    }

    // Provides alert functionality.
    private void alarm(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                message);
        alert.showAndWait();
    }
    
    @FXML
    public void exitWindow() {
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
