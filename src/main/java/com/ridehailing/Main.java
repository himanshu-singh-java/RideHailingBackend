package com.ridehailing;

import model.*;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;
import routing.CityMap;
import service.RideEngine;

public class Main {
    public static void main(String[] args) {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RiderRepository riderRepository = new RiderRepository();
        RideRepository rideRepository = new RideRepository();
        CityMap cityMap = new CityMap();

        RideEngine rideEngine = new RideEngine(vehicleRepository, rideRepository, riderRepository, cityMap);

        System.out.println("Ride-Hailing engine is starting..");

        System.out.println("Simulating Ride Booking");

        int existingRider = 1;

        Rides bookedRide = rideEngine.bookRide(existingRider, "Bike", 1, 5);

        if(bookedRide != null){
            System.out.println("Test Passed: Ride successfully created and saved in Database!");
        }
        else{
            System.out.println("Test Failed: Could not book ride.");
        }
    }
}
