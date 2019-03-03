package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiclesRedisManager implements VehiclesManager {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Optional<Vehicle> findByName(String vehicleName) {
        return vehicleRepository.findById(vehicleName);
    }

    @Override
    public Optional<List<Vehicle>> findInSquare(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat) {
        Box box = new Box(new Point(topLeftLng, topLeftLat), new Point(bottomRightLng, bottomRightLat));
        return vehicleRepository.findByLocationWithin(box);
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
