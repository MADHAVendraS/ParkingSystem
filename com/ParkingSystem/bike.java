package com.ParkingSystem;

class Bike extends Vehicle {
    private int engineCapacity;

    public Bike(String ownerName, String vehicleType, String vehicleName, String vehicleNumber, String ownerMobileNumber, int age, double charge, int slot, int engineCapacity) {
        super(ownerName, vehicleType, vehicleName, vehicleNumber, ownerMobileNumber, age, charge, slot);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public double calculateCharge() {
        return engineCapacity * 0.1;
    }
}
