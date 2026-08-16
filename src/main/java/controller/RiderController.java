package controller;

import model.Rides;
import service.RideEngine;

import java.util.Scanner;

public class RiderController {
    private RideEngine rideEngine;
    private Scanner scanner;

    public RiderController(RideEngine rideEngine){
        this.rideEngine = rideEngine;
        this.scanner = new Scanner(System.in);
    }

    public void startApp(){

        while(true) {
            System.out.println("WELCOME TO RIDE-HAILING APP");
            System.out.println("1. Book a Ride");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            if (choice == 2) {
                System.out.println("Exiting Application. ");
                break;
            } else if (choice == 1) {
                System.out.println("Enter your Rider Id: ");
                int riderId = scanner.nextInt();

                System.out.println("Enter Pickup Node (1 to 5): ");
                int pickupNode = scanner.nextInt();

                System.out.println("Enter Drop Node (1 to 5): ");
                int dropNode = scanner.nextInt();

                System.out.println("Enter Vehicle Type (Bike/Car/Auto): ");
                String vehicleType = scanner.next();

                System.out.println("Searching nearest captain and calculating route...");

                Rides bookRide = rideEngine.bookRide(riderId, vehicleType, pickupNode, dropNode);

                if (bookRide != null) {
                    System.out.println("Booking Confirmed! Have a safe journey.");
                } else {
                    System.out.println("Booking Failed. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
