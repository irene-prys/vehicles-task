package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehiclesManager {
    Optional<Vehicle> findByName(String vehicleName);

    Optional<List<Vehicle>> findInSquare(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat);

    void addOrUpdateVehicle(Vehicle vehicle);
}
