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
import java.util.ArrayList;

/**
 *
 * @author Evan
 */
public class SaveRecords {
    private ArrayList<Booking> bList;
    private ArrayList<StandardRoom> rList;
    private ArrayList<Client> cList;
    private ArrayList<Service> sList;
    private ArrayList<Carpark> pList;
    private ArrayList<Invoice> iList;

    DataSingleton data = new DataSingleton();

    public SaveRecords(ArrayList<Booking> bList, ArrayList<StandardRoom> rList, ArrayList<Client> cList,
                       ArrayList<Service> sList, ArrayList<Carpark> pList, ArrayList<Invoice> iList) {
        this.bList = bList;
        this.rList = rList;
        this.cList = cList;
        this.sList = sList;
        this.pList = pList;
        this.iList = iList;
        
        checkAndCreateFiles();
    }

    public void saveBookingArrayList() {
        Path path = Paths.get("Bookings.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (Booking booking : bList) {
                sb.append(booking.getBookingID()).append(",")
                        .append(booking.getClientID()).append(",")
                        .append(booking.getRoomID()).append(",")
                        .append(booking.getParkID()).append(",")
                        .append(booking.getCheckIn()).append(",")
                        .append(booking.getCheckOut()).append(",")
                        .append(booking.getCharges()).append(",\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void saveRoomArrayList() {
        Path path = Paths.get("Rooms.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (StandardRoom room : rList) {
                sb.append(room.getRoomID()).append(",")
                        .append(room.getBeds()).append(",")
                        .append(room.getDailyRate()).append(",")
                        .append(room.getOccupied()).append("\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void saveClientArrayList() {
        Path path = Paths.get("Clients.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (Client client : cList) {
                sb.append(client.getClientID()).append(",")
                        .append(client.getClientName()).append(",")
                        .append(client.getPhoneNo()).append(",")
                        .append(client.getEmail()).append(",")
                        .append(client.getRegoNo()).append("\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void saveServiceArrayList() {
        Path path = Paths.get("Services.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (Service service : sList) {
                sb.append(service.getBookingID()).append(",")
                        .append(service.getServiceType()).append(",")
                        .append(service.getCost()).append("\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void saveCarparkArrayList() {
        Path path = Paths.get("Carpark.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (Carpark carpark : pList) {
                sb.append(carpark.getParkID()).append(",")
                        .append(carpark.getRegoNo()).append(",")
                        .append(carpark.isOccupied()).append("\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void saveInvoiceArrayList() {
        Path path = Paths.get("Invoices.txt");

        try {
            StringBuilder sb = new StringBuilder();

            for (Invoice invoice : iList) {
                sb.append(invoice.getInvoiceNo()).append(",")
                        .append(invoice.getDate()).append(",")
                        .append(invoice.getPaid()).append("\n");
            }

            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }
    
    public void saveAll() {
        saveBookingArrayList();
        saveRoomArrayList();
        saveClientArrayList();
        saveServiceArrayList();
        saveCarparkArrayList();
        saveInvoiceArrayList();
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
