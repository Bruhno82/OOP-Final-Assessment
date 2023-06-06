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

public class CheckOutHandler {
    private final String fileName; // The name of the file to read/write
    public final ArrayList<CheckOut> checkOuts; // The list of check ins
    
    public CheckOutHandler(String fileName) {
        this.fileName = fileName;
        this.checkOuts = new ArrayList<CheckOut>();
        
        // Read the check in list from the file
        readCheckOutFile();
    }
    
    // Read check in data from a file and populate the booking list
    private void readCheckOutFile() {
        try {
            // Open the file
            Scanner in = new Scanner(new FileReader(fileName));
            String myEntry = "";
            int bookingId = 0;
            int clientId = 0;
            int roomId = 0;
            LocalDate checkOut = null;
            double charges = 0.0;
            
            // Read each line of the file
            while(in.hasNextLine()) {
                myEntry = in.nextLine();
                StringTokenizer st = new StringTokenizer(myEntry,",");
                
                // Parse the tokens and extract booking information
                while (st.hasMoreTokens()) {
                    bookingId = Integer.parseInt(st.nextToken());
                    clientId = Integer.parseInt(st.nextToken());
                    roomId = Integer.parseInt(st.nextToken());
                    checkOut = LocalDate.parse(st.nextToken());
                    charges = Double.parseDouble(st.nextToken());
                }
                
                // Create a new booking object
                CheckOut c = new CheckOut(bookingId, clientId, roomId, checkOut, charges);
                checkOuts.add(c);
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
     * Adds a new check in to the check in list.
     * @param c The check in object to be added.
     */
    public void addCheckOut(CheckOut c) {
        checkOuts.add(c);
    }
    
    /**
     * Saves the check in data to a file
     */
    public void saveCheckOut() {
        try {
            // Open the file
            Formatter out = new Formatter(fileName);
        
            int totalCheckins = checkOuts.size();
        
            // Write check in information to the file
            for (int i = 0; i < totalCheckins; i++) {
                CheckOut c = checkOuts.get(i);
                out.format("%s\n", c.saveString());
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
     public ArrayList<CheckOut> getCheckOuts() {
         return checkOuts;
     }
}