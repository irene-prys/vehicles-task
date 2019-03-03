package demo.task.vehicles.controllers;

import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleService.create(vehicle), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Vehicle> findByName(@PathVariable String name) {
        Optional<Vehicle> vehicle = vehicleService.findByName(name);

        return new ResponseEntity<>(vehicle.isPresent() ? vehicle.get() : null, vehicle.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/rectangle/topLeftLat/{topLeftLat}/topLeftLng/{topLeftLng}/bottomRightLat/{bottomRightLat}/bottomRightLng/{bottomRightLng}")
    public ResponseEntity<List<Vehicle>> findInRectangle(@PathVariable double topLeftLat, @PathVariable double topLeftLng,
                                                         @PathVariable double bottomRightLat, @PathVariable double bottomRightLng) {
        List<Vehicle> vehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}
