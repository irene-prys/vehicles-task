package demo.task.vehicles.utils;

import demo.task.vehicles.exceptions.InvalidCoordinatesException;
import demo.task.vehicles.models.Coordinates;
import demo.task.vehicles.models.Vehicle;

import java.util.*;

public class VehicleCollection {
    private static final int MAX_LONGITUDE_VALUE = 180;
    private static final int MIN_LONGITUDE_VALUE = -180;
    private static final int MAX_LATITUDE_VALUE = 90;
    private static final int MIN_LATITUDE_VALUE = -90;

    private HashMap<String, Vehicle> positionMatrix[][]; // to search quickly in square
    private HashMap<String, Vehicle> vehicles; // to track the position of a certain vehicle

    public VehicleCollection() {
        positionMatrix = new HashMap[MAX_LONGITUDE_VALUE * 2 + 1][MAX_LONGITUDE_VALUE * 2 + 1];// +1 is for zero coordinates
        vehicles = new HashMap<>();
    }

    public void addOrUpdate(Vehicle vehicle) {
        clearOldData(vehicle);

        int lngIndex = getLngIndex(vehicle.getCoordinates().getLongitude());
        int latIndex = getLatIndex(vehicle.getCoordinates().getLatitude());

        HashMap<String, Vehicle> matrixElement = positionMatrix[lngIndex][latIndex];
        if (matrixElement == null) {
            positionMatrix[lngIndex][latIndex] = new HashMap<>();
        }
        positionMatrix[lngIndex][latIndex].put(vehicle.getName(), vehicle);
        vehicles.put(vehicle.getName(), vehicle);
    }

    public List<Vehicle> findByCoordinates(int lng, int lat) {
        Optional<HashMap<String, Vehicle>> foundVehicles = Optional.ofNullable(positionMatrix[getLngIndex(lng)][getLatIndex(lat)]);
        if (!foundVehicles.isPresent()) {
            return Collections.EMPTY_LIST;
        }
        return new ArrayList<>(positionMatrix[getLngIndex(lng)][getLatIndex(lat)].values());
    }

    public Vehicle findByName(String vehicleName) {
        return vehicles.get(vehicleName);
    }

    private void clearOldData(Vehicle vehicle) {
        Optional<Vehicle> oldVehicle = Optional.ofNullable(vehicles.get(vehicle.getName()));
        if (oldVehicle.isPresent()) {
            Coordinates oldCoordinates = oldVehicle.get().getCoordinates();
            int lngIndex = getLngIndex(oldCoordinates.getLongitude());
            int latIndex = getLatIndex(oldCoordinates.getLatitude());
            positionMatrix[lngIndex][latIndex].remove(vehicle.getName());
        }
    }

    private int getLatIndex(int lat) {
        return calculateIndex(lat, MIN_LATITUDE_VALUE, MAX_LATITUDE_VALUE);
    }

    private int getLngIndex(int lng) {
        return calculateIndex(lng, MIN_LONGITUDE_VALUE, MAX_LONGITUDE_VALUE);
    }

    private int calculateIndex(int value, int minValue, int maxValue) {
        if (value < minValue || value > maxValue) {// this check is very important only for this collection; That's why it is here and not in the Vehicle entity
            throw new InvalidCoordinatesException();
        }
        if (value <= 0) {
            return Math.abs(value);
        }
        return maxValue + value;
    }
}
