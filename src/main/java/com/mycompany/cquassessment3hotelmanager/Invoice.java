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
    private LocalDate date;
    private Boolean paid;
    
    public Invoice (String invoiceNo, Boolean paid) {
        this.invoiceNo = invoiceNo;
        this.paid = paid;
        this.date = LocalDate.now();
    }
    public Invoice (String invoiceNo, LocalDate date, Boolean paid) {
        this.invoiceNo = invoiceNo;
        this.date = date; 
        this.paid = paid;       
    }

    // InvoiceNo Getter and Setter
    public String getInvoiceNo() {
        return invoiceNo;
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
        return "Invoice{" + "invoiceNo=" + invoiceNo + ", date=" + date + ", paid=" + paid + '}';
    }
}
