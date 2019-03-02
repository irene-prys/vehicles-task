package demo.task.vehicles.services;

import demo.task.vehicles.managers.VehiclesManager;
import demo.task.vehicles.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Vehicle> findInRectangle(int topLeftLat, int topLeftLng, int bottomRightLat, int bottomRightLng) {
        return vehiclesManager.findInRectangle(topLeftLat, topLeftLng, bottomRightLat, bottomRightLng);
    }
}
