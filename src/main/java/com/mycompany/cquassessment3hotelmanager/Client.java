/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

/**
 *
 * @author Evan
 */
public class Client {
    private String clientID;
    private String clientName;
    private String phoneNo;
    private String email;
    private String regoNo;
    
    public Client(String clientID, String clientName, String phoneNo,
            String email, String regoNo) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.regoNo = regoNo;
    }

    // Client ID Getter
    public String getClientID() {
        return clientID;
    }
    
    // Client ID Setter
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
    // Client Name Getter and Setter
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String phoneNo) {
        this.clientName = phoneNo;
    }
    
    // Phone Number Getter and Setter
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Email Getter and Setter
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Registration Getter and Setter
    public String getRegoNo() {
        return regoNo;
    }
    public void setRegoNo(String regoNo) {
        this.regoNo = regoNo;
    }

    // For saving client data
    public String saveClient() {
        return clientID + "," + clientName + "," + phoneNo + "," + email + "," +
                regoNo + ",";
    }
    
    @Override
    public String toString() {
        return "Client ID: " + clientID + ", Client Name: " + clientName + "\nPhone No: " + phoneNo + ", Email: " + email + ", Rego No: " + regoNo + "\n";
    }  
}
