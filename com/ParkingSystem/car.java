package com.ParkingSystem;

class Car extends Vehicle {
    private boolean hasAC;

    public Car(String ownerName, String vehicleType, String vehicleName, String vehicleNumber, String ownerMobileNumber, int age, double charge, int slot, boolean hasAC) {
        super(ownerName, vehicleType, vehicleName, vehicleNumber, ownerMobileNumber, age, charge, slot);
        this.hasAC = hasAC;
    }

    @Override
    public double calculateCharge() {
        double baseCharge = 10.0;
        if (hasAC) {
            return baseCharge + 5.0;
        } else {
            return baseCharge;
        }
    }
}
