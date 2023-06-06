/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

import java.time.LocalDate;

public class CheckOut {
    public int bookingId;
    public int roomId;
    public int clientId;
    public LocalDate date;
    public double charges;

    public CheckOut(int bookingId, int roomId, int clientId, LocalDate date, double charges) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.clientId = clientId;
        this.date = date;
        this.charges = charges;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }
    
    public String saveString() {
        return bookingId + "," + roomId + "," + clientId + "," + date + "," + charges;
    }
}
