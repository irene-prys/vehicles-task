package demo.task.vehicles.services;

import demo.task.vehicles.managers.VehiclesManager;
import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.utils.GeoCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehiclesManager vehiclesManager;

    @Override
    public void create(Vehicle vehicle) {
        vehiclesManager.addOrUpdateVehicle(vehicle);
    }

    @Override
    public Vehicle findByName(String name) {
        return vehiclesManager.findByName(name);
    }

    @Override
    public List<Vehicle> findInRectangle(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat) {
        double distance = GeoCalculator.calculateDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        Optional<List<Vehicle>> foundData = vehiclesManager.findInSquare(topLeftLng, topLeftLat, distance);
        if (foundData.isPresent()) {
            return foundData.get().stream()
                    .filter(v -> Double.compare(v.getLocation().getX(), bottomRightLng) <= 0 &&
                            Double.compare(v.getLocation().getY(), bottomRightLat) <= 0)
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @PostConstruct
    private void init() {
        Random rand = new Random();
//        vehiclesManager.addOrUpdateVehicle(new Vehicle("Vehicle_" , new Point(74, -86)));
        for (int i = 0; i < 1000_000; i++) {
            if (i % 1000 == 0) {
                vehiclesManager.addOrUpdateVehicle(new Vehicle("Vehicle_" + i, new Point(100, 60)));
            } else {
                vehiclesManager.addOrUpdateVehicle(new Vehicle("Vehicle_" + i, new Point(rand.nextInt((180 + 180) + 1) - 180, rand.nextInt((85 + 85) + 1) - 85)));
            }
        }
    }
}