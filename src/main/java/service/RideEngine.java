package service;

import model.*;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;

public class RideEngine {

    private VehicleRepository vehicleRepository;
    private RideRepository rideRepository;
    private RiderRepository riderRepository;

    public RideEngine(VehicleRepository vehicleRepository,
                      RideRepository rideRepository,
                      RiderRepository riderRepository){
        this.vehicleRepository = vehicleRepository;
        this.rideRepository = rideRepository;
        this.riderRepository = riderRepository;
    }

    public Rides bookRide(int riderId, String vehicleType, double distanceKm){
        System.out.println("Booking initiated for Rider ID: " + riderId + " for a " + vehicleType);

        Riders riders = riderRepository.getRiderById(riderId);
        if(riders == null){
            System.out.println("Error: Rider with ID " + riderId + " not found!");
            return null;
        }

        Vehicles availableVehicle = vehicleRepository.findAvailableVehicle(vehicleType);
        if(availableVehicle == null){
            System.out.println("sorry, no " + vehicleType + "is currently available.");
            return null;
        }

        double estimatedFare = availableVehicle.calculateFare(distanceKm);

        availableVehicle.setVehicleStatus(VehicleStatus.ON_RIDE);

        Rides newRide = new Rides();
        newRide.setRider(riders);
        newRide.setVehicle(availableVehicle);
        newRide.setDistanceKm(distanceKm);
        newRide.setFare(estimatedFare);
        newRide.setRideStatus(RideStatus.ACCEPTED);

        rideRepository.saveRide(newRide);

        System.out.println("Success! Ride booked with " + availableVehicle.getDriverName() + " Total Fare : ₹ " + estimatedFare);

        return  newRide;
    }
}
