package demo.task.vehicles.services;

import demo.task.vehicles.managers.VehiclesManager;
import demo.task.vehicles.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<Vehicle> findInRectangle(int topLeftLng, int topLeftLat, int bottomRightLng, int bottomRightLat) {
//        double distance = calculateMaxDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        double distance = Point2D.distance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        return  vehiclesManager.findInSquare(topLeftLng, topLeftLat , distance);
//        System.out.println("------11111");
//        if (foundData.isPresent()) {
//            System.out.println("------22222");
//            return foundData.get()
//                    .filter(v -> Double.compare(v.getLocation().getX(), bottomRightLng) <= 0 &&
//                            Double.compare(v.getLocation().getY(), bottomRightLat) <= 0)
//                    .collect(Collectors.toList());
//        }
//        System.out.println("------33333");
//        return Collections.EMPTY_LIST;
    }

    private double calculateMaxDistance(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat) {
        double distanceLng = Math.abs(topLeftLng - bottomRightLng);
        double distanceLat = Math.abs(topLeftLat - bottomRightLat);
        double distance = Double.compare(distanceLng, distanceLat) >= 0 ? distanceLng : distanceLat;
        return distance;
    }
}
