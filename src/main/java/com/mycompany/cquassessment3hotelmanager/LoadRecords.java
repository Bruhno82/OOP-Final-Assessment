/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @authors Evan,
 */
public class LoadRecords {   
    private ArrayList<Booking> bList;
    private ArrayList<StandardRoom> rList;
    private ArrayList<Client> cList;
    private ArrayList<Service> sList;
    private ArrayList<Carpark> pList;
    private ArrayList<Invoice> iList;

    DataSingleton data = new DataSingleton();
    
    public LoadRecords() {
        bList = new ArrayList<>();
        rList = new ArrayList<>();
        cList = new ArrayList<>();
        sList = new ArrayList<>();
        pList = new ArrayList<>();
        iList = new ArrayList<>();
        
        checkAndCreateFiles();
    }
    
    public void loadBookingArrayList() {
        Path path = Paths.get("Bookings.txt");

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] bookingData = line.split(",");

                // Parse and extract relevant booking information from the line
                String bookingID = bookingData[0];
                String clientID = bookingData[1];
                String roomID = bookingData[2];
                String parkID = bookingData[3];
                LocalDate checkIn = LocalDate.parse(bookingData[4]);
                LocalDate checkOut = LocalDate.parse(bookingData[5]);
                double charges = Double.parseDouble(bookingData[6]);
                
                // Create a new Booking object using the extracted data fields
                Booking booking = new Booking(bookingID, clientID, roomID, parkID, checkIn, checkOut, charges);

                // Add the Booking object to the bList ArrayList
                bList.add(booking);
            }

            // Set the bList ArrayList in the data singleton
            data.setBookingList(bList);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
    
    public void loadRoomArrayList() {
        Path path = Paths.get("Rooms.txt");
        
        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] roomData = line.split(",");
                
                // Parse and extract other relevant client information from the line
                String roomId = roomData[0];
                int beds = Integer.parseInt(roomData[1]);
                double dailyRate = Double.parseDouble(roomData[2]);
                boolean occupied = Boolean.parseBoolean(roomData[3]);
                
                // Create a new StandardRoom object using the data fields
                StandardRoom room = new StandardRoom(roomId, beds, dailyRate, occupied);
                
                // Add the StandardRoom object to the rList ArrayList
                rList.add(room);
            }
               data.setRoomList(rList);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
    
    public void loadClientArrayList() {
        Path path = Paths.get("Clients.txt");

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] clientData = line.split(",");

                // Parse and extract other relevant client information from the line
                String clientId = clientData[0];
                String clientName = clientData[1];
                String phoneNo = clientData[2];
                String email = clientData[3];
                String regoNo = clientData[4];
                
                // Create a new Client object using the data fields
                Client client = new Client(clientId, clientName, phoneNo, email, regoNo);

                // Add the Client object to the cList ArrayList
                cList.add(client);
            }

            // Set the client counter based on the size of the cList ArrayList
            data.setClientCounter(cList.size() + 1);

            // Set the cList ArrayList in the data singleton
            data.setClientList(cList);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
    
    public void loadServiceArrayList() {
    Path path = Paths.get("Services.txt");
    
    try {
        List<String> lines = Files.readAllLines(path);
        
        for (String line : lines) {
            String[] serviceData = line.split(",");
            
            // Parse and extract other relevant service information from the line
            String roomID = serviceData[0];
            String serviceType = serviceData[1];
            Double cost = Double.parseDouble(serviceData[2]);
            
            // Create a new Service object using the data fields
            Service service = new Service(roomID, serviceType, cost);
            
            // Add the Service object to the sList ArrayList
            sList.add(service);
        }
        
        // Set the sList ArrayList in the data singleton
        data.setServiceList(sList);
    } catch (IOException e) {
        // Handle file reading error
        e.printStackTrace();
    }
}
    
    public void loadCarparkArrayList() {
        Path path = Paths.get("Carpark.txt");
    
        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] carparkData = line.split(",");

                // Parse and extract other relevant carpark information from the line
                String parkID = carparkData[0];
                String regoNo = carparkData[1];
                boolean occupied = Boolean.parseBoolean(carparkData[2]);
                

                // Create a new Carpark object using the data fields
                Carpark carpark = new Carpark(parkID, regoNo, occupied);

                // Add the Carpark object to the pList ArrayList
                pList.add(carpark);
            }

            // Set the pList ArrayList in the data singleton
            data.setParkList(pList);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
    
    public void loadInvoiceArrayList() {
        Path path = Paths.get("Invoices.txt");

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] invoiceData = line.split(",");

                // Parse and extract other relevant invoice information from the line
                String invoiceNo = invoiceData[0];
                LocalDate date = LocalDate.parse(invoiceData[1]);
                Boolean paid = Boolean.parseBoolean(invoiceData[2]);
                
                // Create a new Invoice object using the data fields
                Invoice invoice = new Invoice(invoiceNo, date, paid);

                // Add the Invoice object to the iList ArrayList
                iList.add(invoice);
            }

            // Set the invoice counter based on the size of the iList ArrayList
            data.setInvoiceCounter(iList.size() + 1);

            // Set the iList ArrayList in the data singleton
            data.setInvoiceList(iList);
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
    }
    
        public void checkAndCreateFiles() {
        String[] fileNames = {"Bookings.txt", "Rooms.txt", "Clients.txt", "Services.txt", "Carpark.txt", "Invoices.txt"};

        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // Handle file creation error
                    e.printStackTrace();
                }
            }
        }
    }
}
