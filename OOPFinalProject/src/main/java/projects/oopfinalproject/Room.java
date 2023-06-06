/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

/**
 *
 * @author chris
 */
public class Room {
    public int roomId;
    public int beds;
    public double dailyRate;
    public boolean occupied;

    public Room(int roomId, int beds, double dailyRate, boolean occupied) {
        this.roomId = roomId;
        this.beds = beds;
        this.dailyRate = dailyRate;
        this.occupied = occupied;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    public String saveString() {
        return roomId + "," + beds + "," + dailyRate + "," + occupied;
    }
}

class Suite extends Room {
    private int bedrooms;
    
    public Suite(int roomId, int beds, double dailyRate, boolean occupied, int rooms) {
        super (roomId, beds, dailyRate, occupied);
        this.bedrooms = bedrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }
    
    public String saveString() {
        return roomId + "," + beds + "," + dailyRate + "," + occupied + "," + bedrooms;
    }
}
