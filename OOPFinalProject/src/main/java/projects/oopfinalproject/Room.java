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
    public String roomType;
    public int bedrooms;
    public int beds;
    public double dailyRate;
    public boolean occupied;

    public Room(int roomId, String roomType, int bedrooms, int beds, double dailyRate, boolean occupied) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.bedrooms = bedrooms;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
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
    
    
}
