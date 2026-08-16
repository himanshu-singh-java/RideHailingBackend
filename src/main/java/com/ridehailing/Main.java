package com.ridehailing;

import controller.RiderController;
import model.*;
import repository.RideRepository;
import repository.RiderRepository;
import repository.VehicleRepository;
import routing.CityMap;
import service.RideEngine;

public class Main {
    public static void main(String[] args) {

        System.out.println("Initializing Database and Systems...");

        VehicleRepository vehicleRepository = new VehicleRepository();
        RiderRepository riderRepository = new RiderRepository();
        RideRepository rideRepository = new RideRepository();
        CityMap cityMap = new CityMap();

        RideEngine rideEngine = new RideEngine(vehicleRepository, rideRepository, riderRepository, cityMap);

        RiderController controller = new RiderController(rideEngine);
        controller.startApp();
    }
}
