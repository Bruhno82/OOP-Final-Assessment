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

public class ClientHandler {
    private final String fileName; // The name of the file to read/write
    public final ArrayList<Client> clients; // The list of check ins
    
    public ClientHandler(String fileName) {
        this.fileName = fileName;
        this.clients = new ArrayList<Client>();
        
        // Read the check in list from the file
        readClientFile();
    }
    
    // Read check in data from a file and populate the booking list
    private void readClientFile() {
        try {
            // Open the file
            Scanner in = new Scanner(new FileReader(fileName));
            String myEntry = "";
            int clientId = 0;
            String clientName = "";
            String phoneNo = "";
            String email = "";
            String regoNo = "";
            
            // Read each line of the file
            while(in.hasNextLine()) {
                myEntry = in.nextLine();
                StringTokenizer st = new StringTokenizer(myEntry,",");
                
                // Parse the tokens and extract booking information
                while (st.hasMoreTokens()) {
                    clientId = Integer.parseInt(st.nextToken());
                    clientName = st.nextToken();
                    phoneNo = st.nextToken();
                    email = st.nextToken();
                    regoNo = st.nextToken();
                }
                
                // Create a new booking object
                Client c = new Client(clientId, clientName, phoneNo, email, regoNo);
                clients.add(c);
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
    public void addClient(Client c) {
        clients.add(c);
    }
    
    /**
     * Saves the check in data to a file
     */
    public void saveClient() {
        try {
            // Open the file
            Formatter out = new Formatter(fileName);
        
            int totalCheckins = clients.size();
        
            // Write check in information to the file
            for (int i = 0; i < totalCheckins; i++) {
                Client c = clients.get(i);
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
     public ArrayList<Client> getClients() {
         return clients;
     }
}