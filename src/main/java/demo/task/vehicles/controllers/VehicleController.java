package demo.task.vehicles.controllers;

import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Void> addVehicle() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Vehicle> findByName(@PathVariable String name) {
        return new ResponseEntity<>(vehicleService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/rectangle/topLeftLat/{topLeftLat}/topLeftLng/{topLeftLng}/bottomRightLat/{bottomRightLat}/bottomRightLng/{bottomRightLng}")
    public ResponseEntity<List<Vehicle>> findInRectangle(int topLeftLat, int topLeftLng, int bottomRightLat, int bottomRightLng) {
        List<Vehicle> vehicles = vehicleService.findInRectangle(topLeftLat, topLeftLng, bottomRightLat, bottomRightLng);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}
