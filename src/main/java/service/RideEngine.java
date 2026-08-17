package service;

import model.*;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;
import routing.CityMap;
import model.Rides;

public class RideEngine {

    private VehicleRepository vehicleRepository;
    private RideRepository rideRepository;
    private RiderRepository riderRepository;
    private CityMap cityMap;

    public RideEngine(VehicleRepository vehicleRepository,
                      RideRepository rideRepository,
                      RiderRepository riderRepository, CityMap cityMap){
        this.vehicleRepository = vehicleRepository;
        this.rideRepository = rideRepository;
        this.riderRepository = riderRepository;
        this.cityMap = cityMap;
    }

    public Rides bookRide(int riderId, String vehicleType, int pickupNode, int dropNode){
        System.out.println("Booking initiated for Rider ID: " + riderId + " for a " + vehicleType);

        Riders riders = riderRepository.getRiderById(riderId);
        if(riders == null){
            System.out.println("Error: Rider with ID " + riderId + " not found!");
            return null;
        }

        Vehicles availableVehicle = vehicleRepository.findAvailableVehicle(vehicleType);
        if(availableVehicle == null){
            System.out.println("sorry, no " + vehicleType + " is currently available.");
            return null;
        }


        double estimatedDistance = cityMap.getShortestDistance(pickupNode, dropNode);

        if(estimatedDistance == -1.0){
            System.out.println("Booking Failed: Route not possible between " + pickupNode + " and " + dropNode);
            return null;
        }

        Double estimatedFare = availableVehicle.calculateFare(estimatedDistance);

        availableVehicle.setVehicleStatus(VehicleStatus.ON_RIDE);
        availableVehicle.setCurrentNode(dropNode);
        vehicleRepository.updateVehicle(availableVehicle);

        Rides newRide = new Rides();
        newRide.setRider(riders);
        newRide.setVehicle(availableVehicle);
        newRide.setDistanceKm(estimatedDistance);
        newRide.setFare(estimatedFare);
        newRide.setRideStatus(RideStatus.ACCEPTED);

        rideRepository.saveRide(newRide);

        System.out.println("Success! Ride booked with " + availableVehicle.getDriverName() +
                " (Vehicle ID: " + availableVehicle.getVehicleId() + ") | Total Fare : ₹ " + estimatedFare);

        return  newRide;
    }

    public void completeRide(int rideId){

        Rides ride = rideRepository.getRideById(rideId);

        if(ride == null){
            System.out.println("Error : Ride with ID " + rideId + " not found!");
            return;
        }

        if(ride.getRideStatus() == RideStatus.COMPLETED){
            System.out.println("This ride is already marked as COMPLETED.");
            return;
        }

        ride.setRideStatus(RideStatus.COMPLETED);

        Vehicles vehicles = ride.getVehicle();
        vehicles.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.updateVehicle(vehicles);

        rideRepository.updateRide(ride);

        System.out.println("Success! Ride ID " + rideId + " is now COMPLETED.");
        System.out.println("Driver " + vehicles.getDriverName() +
                " (Vehicle ID: " + vehicles.getVehicleId() + ") is now AVAILABLE...");
    }
}
