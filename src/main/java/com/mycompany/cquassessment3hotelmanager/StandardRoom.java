/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

/**
 *
 * @author Evan
 */
public class StandardRoom {
    private String roomID;
    private int beds;
    private double dailyRate;
    private Boolean occupied;
    
    public StandardRoom(String roomID, int beds, double DailyRate,
            Boolean occupied) {
        this.roomID = roomID;
        this.beds = beds;
        this.dailyRate = dailyRate;
        this.occupied = occupied;
    }

    // Room ID Getter
    public String getRoomID() {
        return roomID;
    }

    // Beds Getter and Setter
    public int getBeds() {
        return beds;
    }
    public void setBeds(int beds) {
        this.beds = beds;
    }

    // DailyRate Getter and Setter
    public double getDailyRate() {
        return dailyRate;
    }
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    // Occupied Getter and Setter
    public Boolean getOccupied() {
        return occupied;
    }
    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    // For saving Room info
    public String saveRoom() {
        return roomID + "," + beds + "," + dailyRate + "," + occupied + ",";
    }
    
    @Override
    public String toString() {
        return "StandardRoom{" + "roomID=" + roomID + ", beds=" + beds + ", dailyRate=" + dailyRate + ", occupied=" + occupied + '}';
    }
}
