package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;

import java.util.List;

public interface VehiclesManager {
    Vehicle findByName(String vehicleName);

    List<Vehicle> findInRectangle(int topLeftLat, int topLeftLng, int bottomRightLat, int bottomRightLng);
}
