package controller;

import model.Rides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RideEngine;

import java.util.List;
import java.util.Scanner;

@Component
public class RiderController {
    private RideEngine rideEngine;
    private Scanner scanner;

    @Autowired
    public RiderController(RideEngine rideEngine){
        this.rideEngine = rideEngine;
        this.scanner = new Scanner(System.in);
    }

    public void startApp(){

        while(true) {
            System.out.println("WELCOME TO RIDE-HAILING APP");
            System.out.println("1. Book a Ride");
            System.out.println("2. Complete a Ride");
            System.out.println("3. Exit");
            System.out.println("4. View Ride History");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            if(choice == 3){
                System.out.println("Exiting Application. ");
                break;
            }
            else if (choice == 2) {
                System.out.println("Enter the Ride ID to complete: ");
                int rideIdToComplete = scanner.nextInt();

                System.out.println("Processing Completion...");
                rideEngine.completeRide(rideIdToComplete);

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
            }
            else if(choice == 4){
                System.out.println("Enter your Rider Id: ");
                int riderId = scanner.nextInt();

                List<Rides> history = rideEngine.getRideHistory(riderId);

                if (history == null || history.isEmpty()) {
                    System.out.println("No rides found for Rider ID: " + riderId);
                } else {
                    System.out.println("RIDE HISTORY FOR RIDER " + riderId);
                    System.out.printf("%-10s | %-11s | %-10s | %-15s | %-15s%n",
                            "RIDE ID", "DISTANCE", "FARE", "STATUS", "VEHICLE TYPE");

                    for (Rides ride : history) {
                        System.out.printf("%-10d | %-8.1f km | ₹ %-8.1f | %-15s | %-15s%n",
                                ride.getRideId(),
                                ride.getDistanceKm(),
                                ride.getFare(),
                                ride.getRideStatus(),
                                ride.getVehicle().getClass().getSimpleName());
                    }
                }

            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
