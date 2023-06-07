package projects.oopfinalproject;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The App class is responsible for starting the application
 * and initializing the data handlers.
 */
public class App extends Application {

    private static Scene scene;
    
    private static BookingHandler bookingData;
    private static CheckInHandler checkInData;
    private static CheckOutHandler checkOutData;
    private static ClientHandler clientData;
    private static InvoiceHandler invoiceData;
    private static RoomHandler roomData;
    private static ServiceHandler serviceData;
    

    @Override
    public void start(Stage stage) throws IOException {
        createDataFiles();
        
        bookingData = new BookingHandler("bookings.txt");
        checkInData = new CheckInHandler("checkIn.txt");
        checkOutData = new CheckOutHandler("checkOut.txt");
        clientData = new ClientHandler("clients.txt");
        invoiceData = new InvoiceHandler("invoices.txt");
        roomData = new RoomHandler("rooms.txt");
        serviceData = new ServiceHandler("services.txt");
        
        
        
        scene = new Scene(loadFXML("main"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static BookingHandler getBookingData() {
        return bookingData;
    }
    
    public static CheckInHandler getCheckInData() {
        return checkInData;
    }
    
    public static CheckOutHandler getCheckOutData() {
        return checkOutData;
    }
    
    public static ClientHandler getClientData() {
        return clientData;
    }
    
    public static InvoiceHandler getInvoiceData() {
        return invoiceData;
    }
    
    public static RoomHandler getRoomData() {
        return roomData;
    }
    
    public static ServiceHandler getServiceData() {
        return serviceData;
    }
    
    // Create file if it does not exist
    private void createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            // File does not exist, create one and populate
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Check if files exist and create them if they do not
    private void createDataFiles() {
        String[] fileNames = {"bookings.txt", "checkIn.txt", "checkOut.txt", "clients.txt", "invoices.txt", "rooms.txt", "services.txt"};
    
        for (String fileName : fileNames) {
            switch (fileName) {
                case "bookings.txt":
                    createFile(fileName);
                    break;
                case "checkIn.txt":
                    createFile(fileName);
                    break;
                case "checkOut.txt":
                    createFile(fileName);
                    break;
                case "clients.txt":
                    createFile(fileName);
                    break;
                case "invoices.txt":
                    createFile(fileName);
                    break;
                case "rooms.txt":
                    createFile(fileName);
                    break;
                case "services.txt":
                    createFile(fileName);
                    break;
            }
        }
    }
    

    public static void main(String[] args) {
        launch();
    }

}