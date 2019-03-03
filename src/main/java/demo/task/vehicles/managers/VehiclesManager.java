package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehiclesManager {
    Vehicle findByName(String vehicleName);

    Optional<List<Vehicle>> findInSquare(double topLeftLng, double topLeftLat, double bottomL);

    void addOrUpdateVehicle(Vehicle vehicle);
}
