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

public class InvoiceHandler {
    private final String fileName; // The name of the file to read/write
    public final ArrayList<Invoice> invoices; // The list of check ins
    
    public InvoiceHandler(String fileName) {
        this.fileName = fileName;
        this.invoices = new ArrayList<Invoice>();
        
        // Read the check in list from the file
        readInvoiceFile();
    }
    
    // Read check in data from a file and populate the booking list
    private void readInvoiceFile() {
        try {
            // Open the file
            Scanner in = new Scanner(new FileReader(fileName));
            String myEntry = "";
            int invoiceId = 0;
            LocalDate date = null;
            double total = 0.0;
            boolean paid = false;
            
            // Read each line of the file
            while(in.hasNextLine()) {
                myEntry = in.nextLine();
                StringTokenizer st = new StringTokenizer(myEntry,",");
                
                // Parse the tokens and extract booking information
                while (st.hasMoreTokens()) {
                    invoiceId = Integer.parseInt(st.nextToken());
                    date = LocalDate.parse(st.nextToken());
                    total = Double.parseDouble(st.nextToken());
                    paid = Boolean.parseBoolean(st.nextToken());
                }
                
                // Create a new booking object
                Invoice i = new Invoice(invoiceId, date, total, paid);
                invoices.add(i);
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
     * @param i The check in object to be added.
     */
    public void addInvoice(Invoice i) {
        invoices.add(i);
    }
    
    /**
     * Saves the check in data to a file
     */
    public void saveInvoice() {
        try {
            // Open the file
            Formatter out = new Formatter(fileName);
        
            int totalCheckins = invoices.size();
        
            // Write check in information to the file
            for (int i = 0; i < totalCheckins; i++) {
                Invoice inv = invoices.get(i);
                out.format("%s\n", inv.saveString());
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
     public ArrayList<Invoice> getInvoices() {
         return invoices;
     }
}
