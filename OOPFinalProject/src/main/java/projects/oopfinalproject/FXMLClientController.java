/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projects.oopfinalproject;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXMLClientController implements Initializable {


    @FXML
    private Button buttonCreateClient;

    @FXML
    private Button buttonDeleteClient;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonSearchClient;

    @FXML
    private Button buttonUpdateClient;

    @FXML
    private TextField txtClientId;

    @FXML
    private TextField txtClientName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNo;
    
    int clientId;
    String clientName;
    String phoneNo;
    String email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonUpdateClient.setVisible(false);
        buttonDeleteClient.setVisible(false);
    }    
    
    @FXML
    private void handleButtonUpdateClient(ActionEvent event) throws IOException {
        clientId = Integer.parseInt(txtClientId.getText());
        
        // delete booking
        Iterator<Client> iterator = App.getClientData().getClients().iterator();
        while (iterator.hasNext()) {
            Client c = iterator.next();
            if (clientId == c.getClientId()) {
                iterator.remove(); // Remove the client from the array
                break;
            }
        }
        
        // add modified booking
        handleButtonCreateClient();
    }

    @FXML
    private void handleButtonSearchClient(ActionEvent event) {
        if (txtClientId.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Enter a client number to search", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                clientId = Integer.parseInt(txtClientId.getText());
                boolean clientFound = false;
                for (Client c : App.getClientData().getClients()) {
                    if (clientId == c.getClientId()) {
                        clientFound = true;
                        txtClientId.setText(String.valueOf(c.getClientId()));
                        txtClientName.setText(String.valueOf(c.getClientName()));
                        txtPhoneNo.setText(c.getPhoneNo());
                        txtEmail.setText(c.getEmail());
                        
                        buttonUpdateClient.setVisible(true);
                        buttonDeleteClient.setVisible(true);
                        break;
                    }
                }
                if (!clientFound) {
                    JOptionPane.showMessageDialog(null, "No client number found", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Client ID must be a number", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    private void handleButtonCreateClient() throws IOException {
        if (txtClientId.getText().isBlank() || txtClientName.getText().isBlank() || txtPhoneNo.getText().isBlank() || txtEmail.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            clientId = Integer.parseInt(txtClientId.getText());
            clientName = txtClientName.getText();
            phoneNo = txtPhoneNo.getText();
            email = txtEmail.getText();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Client ID must be a positive number", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
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

        if (clientFound == true) {
            JOptionPane.showMessageDialog(null, "Client ID already exists", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create the client
        Client c = new Client(clientId, clientName, phoneNo, email);
        App.getClientData().addClient(c);
        JOptionPane.showMessageDialog(null, "Client created", "Success", JOptionPane.INFORMATION_MESSAGE);
        App.setRoot("Main Menu");
    }

    @FXML
    private void handleButtonDeleteClient(ActionEvent event) {
        clientId = Integer.parseInt(txtClientId.getText());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this client?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Iterator<Client> iterator = App.getClientData().getClients().iterator();
                while (iterator.hasNext()) {
                    Client c = iterator.next();
                    if (clientId == c.getClientId()) {
                        iterator.remove(); // Remove the client from the array
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Client deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
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
