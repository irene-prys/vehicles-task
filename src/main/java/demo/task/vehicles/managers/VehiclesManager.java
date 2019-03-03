package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VehiclesManager {
    Vehicle findByName(String vehicleName);

    List<Vehicle> findInSquare(double topLeftLng, double topLeftLat, double  distance);

    void addOrUpdateVehicle(Vehicle vehicle);
}
