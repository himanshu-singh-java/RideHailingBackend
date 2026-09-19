package controller;
import mapper.RideMapper;
import model.RideRequest;
import model.RideResponse;
import model.Rides;
import org.springframework.beans.factory.annotation.Autowired;
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

            List<Rides> rideHistory = rideEngine.getRideHistory(riderId);

            return ResponseEntity.ok(rideHistory);
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestBody RideRequest request) {

            Rides currRide = rideEngine.bookRide(request);

            RideResponse finalResponse = RideMapper.mapToResponse(currRide);

            return ResponseEntity.ok(finalResponse);
    }

    @PutMapping("/complete/{rideId}")
    public ResponseEntity<?> completeRide(@PathVariable int rideId){

            Rides rides = rideEngine.completeRide(rideId);

            RideResponse compResponse = RideMapper.mapToResponse(rides);

            return ResponseEntity.ok(compResponse);

    }

    @DeleteMapping("/delete/{rideId}")
    public ResponseEntity<?> deleteRide(@PathVariable int rideId) {

        rideEngine.deleteRide(rideId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success! Ride with ID " + rideId + " has been permanently deleted.");

        return ResponseEntity.ok(response);
    }

}
