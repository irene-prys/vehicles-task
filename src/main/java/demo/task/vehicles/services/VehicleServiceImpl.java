package demo.task.vehicles.services;

import demo.task.vehicles.managers.VehiclesManager;
import demo.task.vehicles.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehiclesManager vehiclesManager;

    @Override
    public void create(Vehicle vehicle) {
        vehiclesManager.addOrUpdateVehicle(vehicle);
    }

    @Override
    public Optional<Vehicle> findByName(String name) {
        return vehiclesManager.findByName(name);
    }

    @Override
    public List<Vehicle> findInRectangle(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat) {
        Optional<List<Vehicle>> foundData = vehiclesManager.findInSquare(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        if (foundData.isPresent()) {
            return foundData.get();
        }
        return Collections.EMPTY_LIST;
    }

//    @PostConstruct
//    private void init() {
//        Random rand = new Random();
//        for (int i = 0; i < 1000_000; i++) {
//            if (i % 1000 == 0) {
//                vehiclesManager.addOrUpdateVehicle(new Vehicle("Vehicle_" + i, new Point(100, 60)));
//            } else {
//                vehiclesManager.addOrUpdateVehicle(new Vehicle("Vehicle_" + i, new Point(rand.nextInt((180 + 180) + 1) - 180, rand.nextInt((90 + 90) + 1) - 90)));
//            }
//        }
//    }
}