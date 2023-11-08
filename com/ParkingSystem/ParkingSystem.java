package com.ParkingSystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class ParkingSystem extends ParkingRegister{
    private static final String OWNER_USERNAME = "admin";
    private static final String OWNER_PASSWORD = "password";
    private static final ArrayList<String> content=new ArrayList<>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static boolean[] parkingSlots = new boolean[10]; // Assuming 10 parking slots
    static File f;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        f=new File("C:\\Users\\maddy\\OneDrive\\Desktop\\ParkingSystem\\com\\ParkingSystem\\ParkingRegister.txt");
        try{
            f.createNewFile();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("\n\nWelcome to Vehicle Parking System");

        while (true) {
            System.out.println("\nAre you a customer or the owner?\n1.Customer\n2.Owner\n3.Exit the program");
            String userType = scanner.nextLine();

            if (userType.equals("owner")) {
                System.out.print("Enter login ID: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (!username.equals(OWNER_USERNAME) || !password.equals(OWNER_PASSWORD)) {
                    System.out.println("Invalid credentials. Please try again.");
                    continue;
                }

                handleOwnerMode(scanner);
            } else if (userType.equals("customer")) {
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();
                displayCustomerDetails(customerName);
            } else if (userType.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void handleOwnerMode(Scanner scanner) {
        while (true) {
            System.out.println("\nEnter 'new' to input a new vehicle.\nEnter 'show' to display stored vehicles.\nEnter 'remove' to delete a vehicle (by mobile number or slot).\nEnter 'exit' to quit owner mode.");
            String input = scanner.nextLine();
    
            if (input.equals("new")) {
                addVehicle(scanner);
            } else if (input.equals("show")) {
                displayVehicles();
            } else if (input.equals("remove")) {
                System.out.println("Do you want to remove by mobile number (M) or slot number (S)?");
                System.out.println("Type (M) if you want to remove by mobile number (M)");
                System.out.println("Type (S) if you want to remove by slot number (S)?");
                String removalOption = scanner.nextLine();
                if (removalOption.equalsIgnoreCase("M")) {
                    System.out.print("Enter owner's mobile number to remove: ");
                    String ownerMobileNumber = scanner.nextLine();
                    removeVehicleByMobileNumber(ownerMobileNumber);
                } else if (removalOption.equalsIgnoreCase("S")) {
                    System.out.print("Enter slot number to remove: ");
                    int slot = Integer.parseInt(scanner.nextLine());
                    removeVehicleBySlot(slot);
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else if (input.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    
    

    private static void addVehicle(Scanner scanner) {
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Enter type of vehicle (Car/Bike): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter vehicle name: ");
        String vehicleName = scanner.nextLine();
        System.out.print("Enter vehicle number: ");
        String vehicleNumber = scanner.nextLine();
        System.out.print("Enter owner's mobile number: ");

        String ownerMobileNumber = scanner.nextLine();
        int age = 0;

        try {
            System.out.print("Enter age: ");
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for age. Please enter a valid integer.");
            return;
        }

        double charge = 0.0;
        try {
            System.out.print("Enter charge: ");
            charge = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for charge. Please enter a valid number.");
            return;
        }

        System.out.print("Enter parking slot from 1 to 10: ");
        int slot = 0;

        try {
            slot = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for slot. Please enter a valid integer.");
            return;
        }

        if (slot < 1 || slot > 10) {
            System.out.println("Invalid slot number. Please try again.");
            return;
        }

        if (parkingSlots[slot - 1]) {
            System.out.println("Slot already allocated to another customer. Please choose another slot.");
            return;
        }

        parkingSlots[slot - 1] = true;

        Vehicle vehicle;

        if (vehicleType.equalsIgnoreCase("Car")) {
            System.out.print("Does the car have AC? (yes/no): ");
            String hasACString = scanner.nextLine();
            boolean hasAC = hasACString.equalsIgnoreCase("yes");
            vehicle = new Car(ownerName, vehicleType, vehicleName, vehicleNumber, ownerMobileNumber, age, charge, slot, hasAC);
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            System.out.print("Enter engine capacity (in CC): ");
            int engineCapacity = 0;

            try {
                engineCapacity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for engine capacity. Please enter a valid integer.");
                return;
            }

            vehicle = new Bike(ownerName, vehicleType, vehicleName, vehicleNumber, ownerMobileNumber, age, charge, slot, engineCapacity);
        } else {
            System.out.println("Invalid vehicle type. Please choose Car or Bike.");
            return;
        }

        vehicles.add(vehicle);
        content.add(vehicle.toStringwithSpace());
        fileUpdate(f,content);
        System.out.println("Vehicle added successfully.");
    }

    private static void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles stored.");
        } else {
            System.out.println("\nStored Vehicles:");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private static void removeVehicleBySlot(int slot) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getSlot() == slot) {
                parkingSlots[slot - 1] = false;
                vehicles.remove(i);
                content.remove(i);
                fileUpdate(f, content);
                System.out.println("Vehicle removed successfully.");
                return;
            }
        }
        System.out.println("No vehicle found in the provided slot.");
    }

    private static void removeVehicleByMobileNumber(String ownerMobileNumber) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getOwnerMobileNumber().equals(ownerMobileNumber)) {
                parkingSlots[v.getSlot() - 1] = false;
                vehicles.remove(i);
                content.remove(i);
                fileUpdate(f, content);
                System.out.println("Vehicle removed successfully.");
                return;
            }
        }
        System.out.println("No vehicle found with the provided mobile number.");
    }
    

    private static void displayCustomerDetails(String customerName) {
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getOwnerName().equals(customerName)) {
                System.out.println("\nCustomer Details:");
                System.out.println(v);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Customer not found.");
        }
    }
}
