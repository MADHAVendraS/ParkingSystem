package com.ParkingSystem;

abstract class Vehicle {
    private String ownerName;
    private String vehicleType;
    private String vehicleName;
    private String vehicleNumber;
    private String ownerMobileNumber;
    private int age;
    private double charge;
    private int slot;

    public Vehicle(String ownerName, String vehicleType, String vehicleName, String vehicleNumber, String ownerMobileNumber, int age, double charge, int slot) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.ownerMobileNumber = ownerMobileNumber;
        this.age = age;
        this.charge = charge;
        this.slot = slot;
    }

    public abstract double calculateCharge();

    public int getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Owner Name: " + ownerName + "\nType of Vehicle: " + vehicleType +
                "\nVehicle Name: " + vehicleName + "\nVehicle Number: " + vehicleNumber +
                "\nMobile Number: " + ownerMobileNumber + "\nAge: " + age +
                "\nCharge: " + charge + "\nSlot: " + slot;
    }
    public String toStringwithSpace() {
        return "Owner Name: " + ownerName + "\nType of Vehicle: " + vehicleType +
                "\nVehicle Name: " + vehicleName + "\nVehicle Number: " + vehicleNumber +
                "\nMobile Number: " + ownerMobileNumber + "\nAge: " + age +
                "\nCharge: " + charge + "\nSlot: " + slot;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerMobileNumber() {
        return ownerMobileNumber;
    }
}


