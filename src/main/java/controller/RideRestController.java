package controller;


import mapper.RideMapper;
import model.RideRequest;
import model.RideResponse;
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
            Rides currRide = rideEngine.bookRide(request);

            RideResponse finalResponse = RideMapper.mapToResponse(currRide);

            return ResponseEntity.ok(finalResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/complete/{rideId}")
    public ResponseEntity<?> completeRide(@PathVariable int rideId){

        try {

            Rides rides = rideEngine.completeRide(rideId);

            RideResponse compResponse = RideMapper.mapToResponse(rides);

            return ResponseEntity.ok(compResponse);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{rideId}")
    public ResponseEntity<?> deleteRide(@PathVariable int rideId){
        try {
            rideEngine.deleteRide(rideId);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Success! Ride with ID " + rideId + " has been permanently deleted.");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
