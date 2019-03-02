package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehiclesRedisManager implements VehiclesManager {
    @Override
    public Vehicle findByName(String vehicleName) {
        return null;
    }

    @Override
    public List<Vehicle> findInRectangle(int topLeftLat, int topLeftLng, int bottomRightLat, int bottomRightLng) {
        return null;
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        // todo: need to implement
    }
}
