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

public class RoomHandler {
    private final String fileName; // The name of the file to read/write
    public final ArrayList<Room> rooms; // The list of rooms
    
    public RoomHandler(String fileName) {
        this.fileName = fileName;
        this.rooms = new ArrayList<Room>();
        
        // Read the room list from the file
        readRoomFile();
    }
    
    //read room data from a file and populate the room list
    private void readRoomFile() {
        try {
            // Open the file
            Scanner in = new Scanner(new FileReader(fileName));
            String myEntry = "";
            int roomId = 0;
            int beds = 0;
            double dailyRate = 0.0;
            boolean occupied = false;
            int bedrooms = 0;
            
            // Read each line of the file
            
            while(in.hasNextLine()) {
                myEntry = in.nextLine();
                StringTokenizer st = new StringTokenizer(myEntry,",");
                int numTokens = st.countTokens();

                if (numTokens == 4) {
                    // Parse the tokens and extract room information
                    while (st.hasMoreTokens()) {
                        roomId = Integer.parseInt(st.nextToken());
                        beds = Integer.parseInt(st.nextToken());
                        dailyRate = Double.parseDouble(st.nextToken());
                        occupied = Boolean.parseBoolean(st.nextToken());
                    }

                    // Create a new room object
                    Room r = new Room(roomId, beds, dailyRate, occupied);
                    rooms.add(r);
                } else if (numTokens == 5) {
                    // Parse the tokens and extract room information
                    while (st.hasMoreTokens()) {
                        roomId = Integer.parseInt(st.nextToken());
                        beds = Integer.parseInt(st.nextToken());
                        dailyRate = Double.parseDouble(st.nextToken());
                        occupied = Boolean.parseBoolean(st.nextToken());
                        bedrooms = Integer.parseInt(st.nextToken());
                    }

                    // Create a new room object
                    Room r = new Suite(roomId, beds, dailyRate, occupied, bedrooms);
                    rooms.add(r);
                }
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
     * Adds a new room to the rooms list.
     * @param r The room object to be added.
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }
    
    /**
     * Saves the room data to a file
     */
    public void saveRoom() {
        try {
            // Open the file
            Formatter out = new Formatter(fileName);
        
            int totalRooms = rooms.size();
        
            // Write room information to the file
            for (int i = 0; i < totalRooms; i++) {
                Room r = rooms.get(i);
                out.format("%s\n", r.saveString());
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
     * Returns a list of rooms
     */
     public ArrayList<Room> getRooms() {
         return rooms;
     }
}