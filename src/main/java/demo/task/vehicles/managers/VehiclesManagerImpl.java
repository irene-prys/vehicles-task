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
public class VehiclesManagerImpl implements VehiclesManager {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Optional<Vehicle> findById(String id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Optional<List<Vehicle>> findInSquare(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat) {
        Box box = new Box(new Point(topLeftLng, topLeftLat), new Point(bottomRightLng, bottomRightLat));
        return vehicleRepository.findByLocationWithin(box);
    }

    @Override
    public Vehicle addOrUpdateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
