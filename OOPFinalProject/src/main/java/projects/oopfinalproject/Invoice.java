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
public class Invoice {
    public int invoiceNo;
    public LocalDate date;
    public double total;
    public boolean paid;

    public Invoice(int invoiceNo, LocalDate date, double total, boolean paid) {
        this.invoiceNo = invoiceNo;
        this.date = date;
        this.total = total;
        this.paid = paid;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public String saveString() {
        return invoiceNo + "," + date + "," + total + "," + paid;
    }
}
