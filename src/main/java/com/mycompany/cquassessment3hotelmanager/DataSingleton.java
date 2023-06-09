/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.util.ArrayList;

/**
 * This class is used to store and retrieve all data between Controllers. A pretty handy use of static classes!
 * @author Evan
 */
public class DataSingleton {
    private static final DataSingleton dataStorage = new DataSingleton();
    
    private static ArrayList<StandardRoom> roomList = new ArrayList<>();
    private static ArrayList<Booking> bookingList = new ArrayList<>();
    private static ArrayList<Client> clientList = new ArrayList<>();
    private static ArrayList<Service> serviceList = new ArrayList<>();
    private static ArrayList<Carpark> parkList = new ArrayList<>();
    private static ArrayList<Invoice> invoiceList = new ArrayList<>();
    
    private static int bookingCounter = 1;
    private static int clientCounter = 1;
    private static int invoiceCounter = 1;
    
    public static DataSingleton getData() {
        return dataStorage;
    }
    
    // RoomList Getter and Setter
    public ArrayList<StandardRoom> getRoomList() {
        return roomList;
    }
    public void setRoomList(ArrayList<StandardRoom> RoomList) {
        this.roomList = RoomList;
    }

    // BookingList Getter and Setter
    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }
    public void setBookingList(ArrayList<Booking> BookingList) {
        this.bookingList = BookingList;
    }

    // ClientList Getter and Setter
    public ArrayList<Client> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<Client> ClientList) {
        this.clientList = ClientList;
    }

    // ServiceList Getter and Setter
    public ArrayList<Service> getServiceList() {
        return serviceList;
    }
    public void setServiceList(ArrayList<Service> ServiceList) {
        this.serviceList = ServiceList;
    }

    // ParkList Getter and Setter
    public ArrayList<Carpark> getParkList() {
        return parkList;
    }
    public void setParkList(ArrayList<Carpark> ParkList) {
        this.parkList = ParkList;
    }
    
    // ParkList Getter and Setter
    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }
    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
    
    // BookingCounter Getter and Setter
    public int getBookingCounter() {
        return bookingCounter;
    }
    public void setBookingCounter(int bookingCounter) {
        this.bookingCounter = bookingCounter;
    }

    // ClientCounter Getter and Setter
    public int getClientCounter() {
        return clientCounter;
    }
    public void setClientCounter(int clientCounter) {
        this.clientCounter = clientCounter;
    }
    
    // ServiceCounter Getter and Setter
    public int getInvoiceCounter() {
        return invoiceCounter;
    }
    public void setInvoiceCounter(int invoiceCounter) {
        this.invoiceCounter = invoiceCounter;
    }    
}
