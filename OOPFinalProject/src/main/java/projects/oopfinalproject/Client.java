/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projects.oopfinalproject;

/**
 *
 * @author chris
 */
public class Client {
    public int clientId;
    public String clientName;
    public String phoneNo;
    public String email;

    public Client(int clientId, String clientName, String phoneNo, String email) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String saveString() {
        return clientId + "," + clientName + "," + phoneNo + "," + email;
    }
}
