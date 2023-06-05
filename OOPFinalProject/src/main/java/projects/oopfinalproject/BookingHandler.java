/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class BookingHandler {
    private final String fileName; // The name of the file to read/write
    public final ArrayList<Booking> bookings; // The list of bookings
    
    public BookingHandler(String fileName) {
        this.fileName = fileName;
        this.bookings = new ArrayList<Booking>();
        
        // Read the booking list from the file
        readBookingFile();
    }
    
    //read booking data from a file and populate the booking list
    private void readBookingFile() {
        
    }
    
    /**
     * Adds a new booking to the bookings list.
     * @param b The booking object to be added.
     */
    public void addBooking(Booking b) {
        
    }
    
    /**
     * Saves the booking data to a file
     */
    public void saveBooking() {
        
    }
    
    /**
     * Returns a list of bookings
     */
     public ArrayList<Booking> getBookings() {
         return bookings;
     }
}
