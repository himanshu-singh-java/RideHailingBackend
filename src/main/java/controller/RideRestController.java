package controller;


import model.RideRequest;
import model.Rides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import routing.CityMap;
import service.RideEngine;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideRestController {

    @Autowired
    private RideEngine rideEngine;

    @GetMapping("/history/{riderId}")
    public List<Rides> getAllRides(@PathVariable int riderId){
        return rideEngine.getRideHistory(riderId);
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestBody RideRequest request) {

        Rides currRide = rideEngine.bookRide(
                request.getRiderId(),
                request.getVehicleType(),
                request.getPickupNode(),
                request.getDestinationNode()
        );

        if(currRide != null){
            int rideId = currRide.getRideId();
            String driverName = currRide.getVehicle().getDriverName();
            double fare = currRide.getFare();

            String successMessage = "Success! Your " + request.getVehicleType() +
                    " ride is booked.\n" +
                    "Ride ID: " + rideId + "\n" +
                    "Driver: " + driverName + "\n" +
                    "Total Fare: ₹" + fare;

            return ResponseEntity.ok(successMessage);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed! RiderId is wrong or Route selected is invalid or Vehicle is not available");
        }
    }

}
