/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

/**
 *
 * @author chris
 */
public class Service {
    public int bookingId;
    public String serviceType;
    public double price;

    public Service(int bookingId, String serviceType, double price) {
        this.bookingId = bookingId;
        this.serviceType = serviceType;
        this.price = price;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String saveString() {
        return bookingId + "," + serviceType + "," + price;
    }
}
