package service;

import mapper.RideMapper;
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

    public Rides bookRide(RideRequest request){

        Riders riders = riderRepository.findById(request.getRiderId())
                .orElseThrow(() -> new RuntimeException("Error: Rider with ID " + request.getRiderId() + " not found!"));

        boolean hasActiveRide = rideRepository.existsByRider_RiderIDAndRideStatus(request.getRiderId(), RideStatus.ACCEPTED);

        if (hasActiveRide) {
            throw new RuntimeException("Error: You already have an active ride! Please complete or cancel it first.");
        }

        Vehicles availableVehicle = vehicleRepository.findAvailableVehicle(request.getVehicleType());

        if(availableVehicle == null){
            throw new RuntimeException("Error: No available " + request.getVehicleType() + " found right now.");
        }

        double estimatedDistance = cityMap.getShortestDistance(request.getPickupNode(), request.getDropNode());

        if(estimatedDistance == -1.0){
            throw new RuntimeException("Error: Invalid route from node " + request.getPickupNode() + " to " + request.getDropNode());
        }

        Double estimatedFare = availableVehicle.calculateFare(estimatedDistance);

        availableVehicle.setVehicleStatus(VehicleStatus.ON_RIDE);
        availableVehicle.setCurrentNode(request.getDropNode());
        vehicleRepository.save(availableVehicle);

        Rides newRide = RideMapper.mapToEntity(request);
        newRide.setRider(riders);
        newRide.setVehicle(availableVehicle);
        newRide.setDistanceKm(estimatedDistance);
        newRide.setFare(estimatedFare);
        newRide.setRideStatus(RideStatus.ACCEPTED);
        rideRepository.save(newRide);

        return  newRide;
    }

    public Rides completeRide(int rideId){

        Rides ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Error: Ride with ID " + rideId + " not found!"));

        if(ride.getRideStatus() == RideStatus.COMPLETED){
            throw new RuntimeException("This ride is already marked as COMPLETED.");
        }

        ride.setRideStatus(RideStatus.COMPLETED);
        Vehicles vehicles = ride.getVehicle();
        vehicles.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicles);
        rideRepository.save(ride);

        return ride;
    }

    public List<Rides> getRideHistory(int riderId){

        Riders rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Error: Rider with ID " + riderId + " not found!"));

        return  rideRepository.findByRider_RiderIDOrderByRideIdDesc(riderId);
    }

    public void deleteRide(int rideId) {

        Rides rides = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Error: Cannot delete. Ride with ID " + rideId + " not found!"));

        if(rides.getRideStatus() == RideStatus.ACCEPTED){
            Vehicles vehicles = rides.getVehicle();
            vehicles.setVehicleStatus(VehicleStatus.AVAILABLE);
            vehicleRepository.save(vehicles);
        }

        rideRepository.deleteById(rideId);
    }
}
