package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiclesRedisManager implements VehiclesManager {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle findByName(String vehicleName) {// todo: return optional
        Optional<Vehicle> foundData = vehicleRepository.findById(vehicleName);
        if (foundData.isPresent()) {
            return foundData.get();
        }
        return null;
    }

    @Override
    public Optional<List<Vehicle>> findInSquare(double topLeftLng, double topLeftLat, double distance) {
        return vehicleRepository.findByLocationNear(new Point(topLeftLng, topLeftLat), new Distance(distance, Metrics.KILOMETERS));
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
