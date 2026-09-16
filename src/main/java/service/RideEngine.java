package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;
import routing.CityMap;
import model.Rides;

import java.util.List;

@Service
public class RideEngine {

    private VehicleRepository vehicleRepository;
    private RideRepository rideRepository;
    private RiderRepository riderRepository;
    private CityMap cityMap;

    @Autowired
    public RideEngine(VehicleRepository vehicleRepository,
                      RideRepository rideRepository,
                      RiderRepository riderRepository, CityMap cityMap){
        this.vehicleRepository = vehicleRepository;
        this.rideRepository = rideRepository;
        this.riderRepository = riderRepository;
        this.cityMap = cityMap;
    }

    public Rides bookRide(int riderId, String vehicleType, int pickupNode, int dropNode){

        Riders riders = riderRepository.getRiderById(riderId);
        if(riders == null){
            throw new RuntimeException("Error: Rider with ID " + riderId + " not found!");
        }

        Vehicles availableVehicle = vehicleRepository.findAvailableVehicle(vehicleType);
        if(availableVehicle == null){
            throw new RuntimeException("Error: No available " + vehicleType + " found right now.");
        }

        double estimatedDistance = cityMap.getShortestDistance(pickupNode, dropNode);

        if(estimatedDistance == -1.0){
            throw new RuntimeException("Error: Invalid route from node " + pickupNode + " to " + dropNode);
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

        return  newRide;
    }

    public Rides completeRide(int rideId){

        Rides ride = rideRepository.getRideById(rideId);

        if (ride == null) {
            throw new RuntimeException("Error: Ride with ID " + rideId + " not found!");
        }

        if(ride.getRideStatus() == RideStatus.COMPLETED){
            throw new RuntimeException("This ride is already marked as COMPLETED.");
        }

        ride.setRideStatus(RideStatus.COMPLETED);

        Vehicles vehicles = ride.getVehicle();
        vehicles.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.updateVehicle(vehicles);
        rideRepository.updateRide(ride);

        return ride;
    }

    public List<Rides> getRideHistory(int riderId){

        Riders rider = riderRepository.getRiderById(riderId);

        if(rider == null){
            throw new RuntimeException("Error: Rider with ID " + riderId + " not found!");
        }

        return  rideRepository.getRideHistoryByRiderId(riderId);
    }
}
