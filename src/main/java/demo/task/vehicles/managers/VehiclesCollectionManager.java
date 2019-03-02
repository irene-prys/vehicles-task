package demo.task.vehicles.managers;


import demo.task.vehicles.models.Vehicle;
import demo.task.vehicles.utils.VehicleCollection;

import java.util.ArrayList;
import java.util.List;

public class VehiclesCollectionManager implements VehiclesManager {
    private VehicleCollection vehicleCollection = new VehicleCollection();

    @Override
    public Vehicle findByName(String vehicleName) {
        return vehicleCollection.findByName(vehicleName);
    }

    @Override
    public List<Vehicle> findInRectangle(int topLeftLat, int topLeftLng, int bottomRightLat, int bottomRightLng) {
        List<Vehicle> foundVehicles = new ArrayList<>();
        for (int i = topLeftLng; i <= bottomRightLng; i++) {
            for (int j = topLeftLat; j <= bottomRightLat; j++) {
                foundVehicles.addAll(vehicleCollection.findByCoordinates(i, j));
            }
        }
        return foundVehicles;
    }

    @Override
    public void addOrUpdateVehicle(Vehicle vehicle) {
        vehicleCollection.addOrUpdate(vehicle);
    }
}
