package service;

import model.Rides;
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
        return  null;
    }
}
