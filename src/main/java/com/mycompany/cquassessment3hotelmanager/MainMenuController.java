/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Evan
 */
public class MainMenuController implements Initializable {


    @FXML
    private TextField roomsField;
    @FXML
    private TextField clientsField;
    @FXML
    private TextField bookingsField;
    @FXML
    private Button searchRoomsBtn;
    @FXML
    private Button searchClientsBtn;
    @FXML
    private Button searchInvoiceBtn;
    @FXML
    private Button showClientsBtn;
    @FXML
    private Button showRoomsBtn;
    @FXML
    private Button showInvoicesBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField invoicesField;
    @FXML
    private Button showBookingsBtn;
    @FXML
    private TextField parksField;
    @FXML
    private Button searchParksBtn;
    @FXML
    private Button showParksBtn;
    @FXML
    private TextArea displayArea;
    @FXML
    private Button checkInBtn;
    @FXML
    private Button checkOutBtn;
    @FXML
    private Button roomServiceBtn;
    @FXML
    private Button createBookingBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    private ArrayList<StandardRoom> roomList;
    private ArrayList<Booking> bookingList;
    private ArrayList<Carpark> parkList;
    private ArrayList<Client> clientList;
    private ArrayList<Client> invoiceList;


    // Functionality for the various "Search" buttons
    // Searches Room ArrayList
    @FXML
    private void searchRooms() {
        displayArea.setText("");
        String prompt = roomsField.getText();
        DataSingleton data = DataSingleton.getData();
        for(StandardRoom room: data.getRoomList()) {
            if(room.getRoomID().equals(prompt) == true) {
                displayArea.setText(room.toString());
                break;
            }
            else {
                displayArea.setText("No room could be found.");
            }
        }
    }  
    // Searchs Park ArrayList
    @FXML
    private void searchParks() {
        displayArea.setText("");
        String prompt = parksField.getText();
        DataSingleton data = DataSingleton.getData();
        for(Carpark park: data.getParkList()) {
            if(park.getParkID().equals(prompt) == true) {
                displayArea.setText(park.toString());
                break;
            }
            else {
                displayArea.setText("No park could be found.");
            }
        }        
    } 
    // Searches Bookings ArrayList
    @FXML
    private void searchBookings() throws IOException {
        String prompt = bookingsField.getText();
        int index;
        
        DataSingleton data = DataSingleton.getData();
        ArrayList<Booking> bList = data.getBookingList();
        
        for(Booking booking: bList) {
            if(booking.getBookingID().equals(prompt) == false) {
                displayArea.setText("No booking could be found.");
            }
            else {
                index = bList.indexOf(booking);
                
                FXMLLoader displayBookingLoader = new FXMLLoader(this.getClass().getResource("DisplayBooking.fxml"));
                Parent root = displayBookingLoader.load();

                DisplayBookingController displayBooking = displayBookingLoader.getController();
                displayBooking.setValues(index);
                
                Stage displayBookingStage = new Stage();
                Scene displayBookingScene = new Scene(root);
                displayBookingStage.setScene(displayBookingScene);
                displayBookingStage.initModality(Modality.APPLICATION_MODAL);
                displayBookingStage.show();
            }
        }
    } 
    // Searchs Clients ArrayList
    @FXML   
    private void searchClients() throws IOException {
        String prompt = clientsField.getText();
        int index;
        
        DataSingleton data = DataSingleton.getData();
        ArrayList<Client> cList = data.getClientList();
        
        for(Client client: cList) {
            if(client.getClientID().equals(prompt) == false) {
                displayArea.setText("No booking could be found.");
            }
            else {
                index = cList.indexOf(client);
                
                FXMLLoader displayClientLoader = new FXMLLoader(this.getClass().getResource("DisplayClient.fxml"));
                Parent root = displayClientLoader.load();

                DisplayClientController displayClient = displayClientLoader.getController();
                displayClient.setValues(index);
                
                Stage displayClientStage = new Stage();
                Scene displayClientScene = new Scene(root);
                displayClientStage.setScene(displayClientScene);
                displayClientStage.initModality(Modality.APPLICATION_MODAL);
                displayClientStage.show();
            }
        }              
    } 
    // Searches Invoices ArrayList
    private void searchInvoices() throws IOException {
        // This method should trigger a new scene with the Invoices information window. If it cannot be found, the display area indicates this with a message. 
        // Leave alone until Nathaniel has finished his class
    }
    
    
    // Functionality for the various "Show All" buttons
    @FXML
    private void showRooms() {
        displayArea.setText("");
        DataSingleton data = DataSingleton.getData();
        roomList = data.getRoomList();
        for(StandardRoom room: roomList) {
            displayArea.appendText(room.toString() + "\n");
        }
    }
    @FXML
    private void showBookings() {
        displayArea.setText("");
        DataSingleton data = DataSingleton.getData();
        for(Booking booking: data.getBookingList()) {
            displayArea.appendText(booking.toString() + "\n");
        }
    }
    @FXML 
    private void showParks() {
        displayArea.setText("");
        DataSingleton data = DataSingleton.getData();
        parkList = data.getParkList();
        for(Carpark park: parkList) {
            displayArea.appendText(park.toString() + "\n");
        }        
    }
    @FXML
    private void showClients() {
        displayArea.setText("");
        DataSingleton data = DataSingleton.getData();
        clientList = data.getClientList();
        for(Client client: clientList) {
            displayArea.appendText(client.toString() + "\n");
        }
    }
    @FXML
    private void showInvoices() {
        displayArea.setText("");
        DataSingleton data = DataSingleton.getData();
        for(Invoice invoice: data.getInvoiceList()) {
            displayArea.appendText(invoice.toString() + "\n");
        }
    }
    
    
    // Functionality behind the buttons to the side of the display area
    @FXML
    private void bookingWindow() throws IOException {
        FXMLLoader bookingLoader = new FXMLLoader(this.getClass().getResource("BookingScreen.fxml"));
        Parent root = bookingLoader.load();
        
        BookingScreenController bookingController = bookingLoader.getController();

        Stage bookingStage = new Stage();
        Scene bookingScene = new Scene(root);
        bookingStage.setScene(bookingScene);
        bookingStage.initModality(Modality.APPLICATION_MODAL);
        bookingStage.show();
    }
    @FXML
    private void checkInWindow() throws IOException {
        FXMLLoader checkInLoader = new FXMLLoader(this.getClass().getResource("CheckIn.fxml"));
        Parent root = checkInLoader.load();
        
        CheckInController checkInController = checkInLoader.getController();

        Stage checkInStage = new Stage();
        Scene checkInScene = new Scene(root);
        checkInStage.setScene(checkInScene);
        checkInStage.initModality(Modality.APPLICATION_MODAL);
        checkInStage.show();
    }
    @FXML
    private void checkOutWindow() throws IOException {
        FXMLLoader checkOutLoader = new FXMLLoader(this.getClass().getResource("CheckOut.fxml"));
        Parent root = checkOutLoader.load();
        
        CheckOutController checkOutController = checkOutLoader.getController();

        Stage checkOutStage = new Stage();
        Scene checkOutScene = new Scene(root);
        checkOutStage.setScene(checkOutScene);
        checkOutStage.initModality(Modality.APPLICATION_MODAL);
        checkOutStage.show();
    }
    
    @FXML
    private void saveButton() {
        
    }
  
    // Exit functionality
    @FXML
    private void exitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will close"
                + " the program. Are you sure?");
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }
}
