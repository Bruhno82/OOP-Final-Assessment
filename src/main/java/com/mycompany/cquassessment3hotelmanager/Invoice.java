/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

import java.time.LocalDate;

/**
 *
 * @author Evan
 */
public class Invoice {
    private String invoiceNo;
    private String clientID;
    private double price;
    private LocalDate date;
    private Boolean paid;
    
    public Invoice (String invoiceNo, String clientID, double price, LocalDate date, Boolean paid) {
        this.invoiceNo = invoiceNo;
        this.clientID = clientID;
        this.price = price;
        this.paid = paid;
        this.date = LocalDate.now();
    }

    // InvoiceNo Getter and Setter
    public String getInvoiceNo() {
        return invoiceNo;
    }
    
    // ClientID Getter and Setter
    public String getClientID() {
        return clientID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
    // Price Getter and Setter
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // Date Getter and Setter
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Paid Getter and Setter
    public Boolean getPaid() {
        return paid;
    }
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    // For saving Invoice info
    public String saveInvoice() {
        return invoiceNo + "," + date + "," + paid + ",";
    }
    
    @Override
    public String toString() {
        return "Invoice Number: " + invoiceNo + ", Billed to: " + clientID + ", Date Issued: " + date + "\nTotal Amount: $" + price + ", Paid: " + paid + "\n";
    }
}
