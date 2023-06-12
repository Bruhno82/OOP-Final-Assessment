/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.time.LocalDate;

/**
 *
 * @author Evan
 */
public class Booking {
    private String bookingID;
    private String clientID;
    private String roomID;
    private String parkID;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double charges;
    
    public Booking(String bookingID, String clientID, String roomID,
            String parkID, LocalDate checkIn, LocalDate checkOut,
            double charges) {
        this.bookingID = bookingID;
        this.clientID = clientID;
        this.roomID = roomID;
        this.parkID = parkID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.charges = charges;
    }

    // Booking ID Getter
    public String getBookingID() {
        return bookingID;
    }
    
    // Booking ID Setter
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    

    // Client ID Getter and Setter.
    public String getClientID() {
        return clientID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    // Room ID Getter and Setter
    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    
    // Park ID Getter and Setter
    public String getParkID() {
        return parkID;
    }
    public void setParkID(String parkID) {
        this.parkID = parkID;
    }
    
    // CheckIn Getter and Setter
    public LocalDate getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }
    
    // CheckOut Getter and Setter
    public LocalDate getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    // Charges Getter and Setter
    public double getCharges() {
        return charges;
    }
    public void setCharges(double charges) {
        this.charges = charges;
    }
    
    // For saving Booking info
    public String saveBooking() {
        return bookingID + "," + clientID + "," + roomID + "," + parkID + "," +
                checkIn + "," + checkOut + "," + charges + ",";
    }
    
    @Override
    public String toString() {
        return "Booking Number: " + bookingID + ", Client ID: " + clientID + ", Room No: " + roomID + ", Carpark: " + parkID + "\nBegins " + checkIn + ", Ends: " + checkOut + ", Costs: $" + charges +"\n";
    } 
}
