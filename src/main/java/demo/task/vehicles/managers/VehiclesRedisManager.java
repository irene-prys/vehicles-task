package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class VehiclesRedisManager implements VehiclesManager {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle findByName(String vehicleName) {// todo: return optional
        Optional<Vehicle> foundData = vehicleRepository.findById(vehicleName);
        if(foundData.isPresent()) {
            return foundData.get();
        }
        return null;
    }

    @Override
    public List<Vehicle> findInSquare(double topLeftLng, double topLeftLat, double  distance) {
        return  vehicleRepository.findByLocationNear(new Point(topLeftLng, topLeftLat), new Distance(distance, Metrics.MILES));
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
