package controller;

import model.Rides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RideEngine;

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
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
