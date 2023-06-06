/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;

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
        try {
            // Open the file
            Scanner in = new Scanner(new FileReader(fileName));
            String myEntry = "";
            int bookingId = 0;
            int clientId = 0;
            int roomId = 0;
            LocalDate checkIn = null;
            LocalDate checkOut = null;
            double charges = 0.0;
            int carpark = 0; // May need to move this to another part of the app
            
            // Read each line of the file
            while(in.hasNextLine()) {
                myEntry = in.nextLine();
                StringTokenizer st = new StringTokenizer(myEntry,",");
                
                // Parse the tokens and extract booking information
                while (st.hasMoreTokens()) {
                    bookingId = Integer.parseInt(st.nextToken());
                    clientId = Integer.parseInt(st.nextToken());
                    roomId = Integer.parseInt(st.nextToken());
                    checkIn = LocalDate.parse(st.nextToken());
                    checkOut = LocalDate.parse(st.nextToken());
                    charges = Double.parseDouble(st.nextToken());
                    carpark = Integer.parseInt(st.nextToken());
                }
                
                // Create a new booking object
                Booking b = new Booking(bookingId, clientId, roomId, checkIn, checkOut, charges, carpark);
                bookings.add(b);
            }
            
            // Close the file
            in.close();
        } catch (ArrayIndexOutOfBoundsException ex){
            // Handle the exception when the file format is incorrect
            JOptionPane.showMessageDialog(null,"The application could not read the data from the save file","Invalid data",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (FileNotFoundException ex) {
            // Handle the exception when the file is not found
            JOptionPane.showMessageDialog(null,"The file could not be found","File not found",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    /**
     * Adds a new booking to the bookings list.
     * @param b The booking object to be added.
     */
    public void addBooking(Booking b) {
        bookings.add(b);
    }
    
    /**
     * Saves the booking data to a file
     */
    public void saveBooking() {
        try {
            // Open the file
            Formatter out = new Formatter(fileName);
        
            int totalBookings = bookings.size();
        
            // Write booking information to the file
            for (int i = 0; i < totalBookings; i++) {
                Booking b = bookings.get(i);
                out.format("%s\n", b.saveString());
            }
            // Close the file
            out.close();
        } catch (FileNotFoundException ex) {
            // Handle the exception when the file is not found during saving
            JOptionPane.showMessageDialog(null,"The file could not be found","File not found",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    /**
     * Returns a list of bookings
     */
     public ArrayList<Booking> getBookings() {
         return bookings;
     }
}