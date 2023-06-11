/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

/**
 *
 * @author Evan
 */
public class Carpark {
    private String parkID;
    private String regoNo;
    private boolean occupied;
    
    Carpark(String parkID, String regoNo, boolean occupied) {
        this.parkID = parkID;
        this.regoNo = regoNo;
        this.occupied = occupied;
    }

    // ParkID Getter
    public String getParkID() {
        return parkID;
    }

    // RegoNo Getter and Setter
    public String getRegoNo() {
        return regoNo;
    }
    public void setRegoNo(String regoNo) {
        this.regoNo = regoNo;
    }

    // Occupied Getter and Setter
    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // For saving Park info
    public String saveParks() {
        return parkID + "," + regoNo + "," + occupied + ",";
    }
    
    @Override
    public String toString() {
        return "Park ID: " + parkID + ", Registration: " + regoNo + ", Occupied: " + occupied + "\n";
    }
    
    
    
}
