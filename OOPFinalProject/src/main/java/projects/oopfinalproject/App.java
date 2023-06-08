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
    
    public static BookingHandler bookingData;
    public static CheckInHandler checkInData;
    public static CheckOutHandler checkOutData;
    public static ClientHandler clientData;
    public static InvoiceHandler invoiceData;
    public static RoomHandler roomData;
    public static ServiceHandler serviceData;
    

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
        
        
        scene = new Scene(loadFXML("Main Menu"), 640, 480);
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
    private void createDataFiles() throws IOException {
        String[] fileNames = {"bookings.txt", "checkIn.txt", "checkOut.txt", "clients.txt", "invoices.txt", "rooms.txt", "services.txt"};
    
        for (String fileName : fileNames) {
            File file = new File(fileName);
            switch (fileName) {
                case "bookings.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    break;
                case "checkIn.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    break;
                case "checkOut.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    break;
                case "clients.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                        
                        ClientHandler ch = new ClientHandler(fileName);
                    
                        Client client1 = new Client(1, "John Smith", "0412 345 678", "john@example.com");
                        Client client2 = new Client(2, "Jane Doe", "0413 456 789", "jane@example.com");
                        Client client3 = new Client(3, "Michael Johnson", "0414 567 890", "michael@example.com");
                        Client client4 = new Client(4, "Emily Davis", "0415 678 901", "emily@example.com");
                        Client client5 = new Client(5, "David Wilson", "0416 789 012", "david@example.com");

                        ch.addClient(client1);
                        ch.addClient(client2);
                        ch.addClient(client3);
                        ch.addClient(client4);
                        ch.addClient(client5);

                        ch.saveClient();
                    }
                    break;
                case "invoices.txt":
                    createFile(fileName);
                    break;
                case "rooms.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                        
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
                    }
                    break;
                case "services.txt":
                    if (!file.exists()) {
                        file.createNewFile();
                        
                        ServiceHandler sh = new ServiceHandler(fileName);
                        // Create multiple Service objects
                        Service service1 = new Service(1, "Cleaning", 10.99);
                        Service service2 = new Service(2, "Laundry", 15.99);
                        Service service3 = new Service(3, "Room Service", 20.99);

                        // Add the Service objects to the services list
                        sh.addService(service1);
                        sh.addService(service2);
                        sh.addService(service3);
                            
                        sh.saveService();
                        }
                    break;
            }
        }
    }
    
    public static void saveData() {
        bookingData.saveBooking();
        checkInData.saveCheckIn();
        checkOutData.saveCheckOut();
        clientData.saveClient();
        invoiceData.saveInvoice();
        roomData.saveRoom();
        serviceData.saveService();
    }
    

    public static void main(String[] args) {
        launch();
    }

}