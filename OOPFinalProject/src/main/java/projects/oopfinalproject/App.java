package projects.oopfinalproject;

import java.io.File;
import java.io.FileWriter;
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
        checkInData = new CheckInHandler("CheckIn.txt");
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
                    RoomHandler rh = new RoomHandler(fileName);
                    // Create rooms
                    for (int i = 1; i <= 30; i++) {
                        Room room = new Room(i, 2, 100.0, false);
                        rh.addRoom(room);
                    }
                    
                    // Create suites
                    for (int i = 31; i <= 40; i++) {
                        Suite suite = new Suite(i, 4, 200.0, false, 2);
                        rh.addRoom(suite);
                    }
                    
                    rh.saveRoom();
                    break;
                case "services.txt":
                    createFile(fileName);
                    ServiceHandler sh = new ServiceHandler(fileName);

                    // Create multiple Service objects
                    Service service1 = new Service(1, "Cleaning", 10.99);
                    Service service2 = new Service(2, "Laundry", 15.99);
                    Service service3 = new Service(3, "Room Service", 20.99);

                    // Add the Service objects to the services list
                    sh.addService(service1);
                    sh.addService(service2);
                    sh.addService(service3);

                    // Save the updated service data to the file
                    sh.saveService();
                    break;
            }
        }
    }
    

    public static void main(String[] args) {
        launch();
    }

}