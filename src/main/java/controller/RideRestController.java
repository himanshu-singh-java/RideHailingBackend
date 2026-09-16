package controller;


import model.RideRequest;
import model.Riders;
import model.Rides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RideEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
public class RideRestController {

    @Autowired
    private RideEngine rideEngine;

    @GetMapping("/history/{riderId}")
    public ResponseEntity<?> getAllRides(@PathVariable int riderId){
        try{
            List<Rides> rideHistory = rideEngine.getRideHistory(riderId);
            return ResponseEntity.ok(rideHistory);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestBody RideRequest request) {

        try {
            Rides currRide = rideEngine.bookRide(
                    request.getRiderId(),
                    request.getVehicleType(),
                    request.getPickupNode(),
                    request.getDropNode()
            );


            int rideId = currRide.getRideId();
            String driverName = currRide.getVehicle().getDriverName();
            double fare = currRide.getFare();

            String successMessage = "Success! Your " + request.getVehicleType() +
                    " ride is booked.\n" +
                    "Ride ID: " + rideId + "\n" +
                    "Driver: " + driverName + "\n" +
                    "Total Fare: ₹" + fare;

            return ResponseEntity.ok(successMessage);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/complete/{rideId}")
    public ResponseEntity<?> completeRide(@PathVariable int rideId){

        try {

            Rides rides = rideEngine.completeRide(rideId);

            double fare = rides.getFare();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Your ride has been completed successfully! Total fare is: ₹" + fare);
            response.put("data", rides);

            return ResponseEntity.ok(response);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
