package controller;

import model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleRestController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/status/{type}")
    public ResponseEntity<?> getVehicleByType(@PathVariable String type){
        List<Vehicles> vehicles = new ArrayList<>();

        switch (type.toUpperCase()){
            case "BIKE":
                vehicles = vehicleRepository.findAllBikes();
                break;
            case "CAR":
                vehicles = vehicleRepository.findAllCars();
                break;
            case "AUTO":
                vehicles = vehicleRepository.findAllAutos();
                break;
        }

        if(vehicles == null || vehicles.isEmpty()){
            return ResponseEntity.ok(new ArrayList<>());
        }
        return ResponseEntity.ok(vehicles);
    }
}
