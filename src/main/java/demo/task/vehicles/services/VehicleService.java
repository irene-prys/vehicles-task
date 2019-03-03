package demo.task.vehicles.services;

import demo.task.vehicles.models.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle findByName(String name);

    void create(Vehicle vehicle);

    List<Vehicle> findInRectangle(double topLeftLng, double topLeftLat, double bottomRightLng, double bottomRightLat);
}
