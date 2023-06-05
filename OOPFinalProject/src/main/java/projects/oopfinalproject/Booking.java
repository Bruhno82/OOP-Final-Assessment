/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class Booking {
    public int bookingId;
    public int clientId;
    public int roomId;
    public LocalDate checkIn;
    public LocalDate checkOut;
    public double charges;
    public int carpark;

    public Booking(int bookingId, int clientId, int roomId, LocalDate checkIn, LocalDate checkOut, double charges, int carpark) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.charges = charges;
        this.carpark = carpark;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public int getCarpark() {
        return carpark;
    }

    public void setCarpark(int carpark) {
        this.carpark = carpark;
    }
    
}
