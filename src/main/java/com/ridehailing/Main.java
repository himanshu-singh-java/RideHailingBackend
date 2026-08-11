package com.ridehailing;

import model.*;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;
import service.RideEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ride-Hailing engine is starting..");

        VehicleRepository vehicleRepository = new VehicleRepository();
        RiderRepository riderRepository = new RiderRepository();
        RideRepository rideRepository = new RideRepository();

        RideEngine rideEngine = new RideEngine(vehicleRepository, rideRepository, riderRepository);

        Riders rider1 = new Riders();
        rider1.setRiderName("Himanshu Singh");
        riderRepository.saveRiders(rider1);

        Bike bike = new Bike();
        bike.setBikeModel("Hf");
        bike.setBikeType("electric");
        bike.setDriverName("Mohit Giri");
        bike.setVehicleStatus(VehicleStatus.AVAILABLE);
        bike.setRating(4.9);
        bike.setCurrentNode(2);
        bike.setHasExtraHelmet(true);
        bike.setParcelDeliveryEnabled(false);
        vehicleRepository.saveVehicle(bike);

        Car car = new Car();
        car.setDriverName("Suresh Driver");
        car.setVehicleStatus(VehicleStatus.AVAILABLE);
        car.setRating(4.9);
        car.setCurrentNode(2);
        vehicleRepository.saveVehicle(car);

        Auto auto = new Auto();
        auto.setDriverName("Mukesh Auto");
        auto.setVehicleStatus(VehicleStatus.AVAILABLE);
        auto.setRating(4.5);
        auto.setCurrentNode(3);
        auto.setPassengerCapacity(3);
        auto.setSharedAuto(false);
        vehicleRepository.saveVehicle(auto);

        System.out.println("Simulating Ride Booking");

        Rides bookedRide = rideEngine.bookRide(rider1.getRiderID(), "Bike", 12.5);

        if(bookedRide != null){
            System.out.println("Test Passed: Ride successfully created and saved in Database!");
        }
        else{
            System.out.println("Test Failed: Could not book ride.");
        }
    }
}
