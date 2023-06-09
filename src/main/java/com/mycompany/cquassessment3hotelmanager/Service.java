/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cquassessment3hotelmanager;

/**
 *
 * @author Evan
 */
public class Service {
    private String roomID;
    private String serviceType;
    private Double cost;
    
    public Service(String roomID, String serviceType, Double cost) {
        this.roomID = roomID;
        this.serviceType = serviceType;
        this.cost = cost;
    }

    // Room ID Getter and Setter
    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    // Service Type Getter and Setter
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    // Cost Getter and Setter
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }

    // For saving Service info
    public String saveService() {
        return roomID + "," + serviceType + "," + cost + ",";
    }
    
    @Override
    public String toString() {
        return "Service Provided to: " + roomID + " Service: " + serviceType + " Cost:" + cost;
    }
}
