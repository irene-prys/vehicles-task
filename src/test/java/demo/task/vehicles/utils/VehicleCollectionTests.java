package demo.task.vehicles.utils;

import demo.task.vehicles.exceptions.InvalidCoordinatesException;
import demo.task.vehicles.models.Coordinates;
import demo.task.vehicles.models.Vehicle;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VehicleCollectionTests {

    @Test
    public void shouldAddVehicleWithMinusCoordinate() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("two", new Coordinates(-1, -2)));
        assertEquals(1, vehicles.findByCoordinates(-1, -2).size());
    }

    @Test
    public void shouldWorFindByCoordinatesWhenCollectionIsEmpty() {
        VehicleCollection vehicles = new VehicleCollection();
        assertEquals(0, vehicles.findByCoordinates(1, 2).size());
    }

    @Test
    public void shouldWorkFindByNameWhenCollectionIsEmpty() {
        VehicleCollection vehicles = new VehicleCollection();
        assertEquals(null, vehicles.findByName("one"));
    }

    @Test
    public void shouldAddVehicleWithPositiveCoordinate() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(1, 2)));
        assertEquals(1, vehicles.findByCoordinates(1, 2).size());
    }

    @Test
    public void shouldUpdateDataProperly() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(1, 2)));
        assertEquals(1, vehicles.findByCoordinates(1, 2).size());

        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(4, 5)));
        assertEquals(1, vehicles.findByCoordinates(4, 5).size());
        assertEquals(0, vehicles.findByCoordinates(1, 2).size());

        assertEquals("one", vehicles.findByName("one").getName());
    }

    @Test
    public void shouldAddVehicleWithZeroCoordinate() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(0, 0)));
        assertEquals(1, vehicles.findByCoordinates(0, 0).size());
    }

    @Test
    public void shouldAddVehicleWithMaxCoordinates() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(0, 0)));
        assertEquals(1, vehicles.findByCoordinates(0, 0).size());
    }

    @Test
    public void shouldAddVehicleWithMinCoordinates() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(-180, -90)));
        assertEquals(1, vehicles.findByCoordinates(-180, -90).size());
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void shouldThrowExceptionIfLongitudeTooBig() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(181, 0)));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void shouldThrowExceptionIfLatitudeTooBig() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(0, 91)));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void shouldThrowExceptionIfLongitudeTooSmall() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(-181, 0)));
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void shouldThrowExceptionIfLatitudeTooSmall() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(0, -91)));
    }

    @Test
    public void shouldAddSeveralElements() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(1, 2)));
        vehicles.addOrUpdate(new Vehicle("two", new Coordinates(-1, -2)));
        vehicles.addOrUpdate(new Vehicle("three", new Coordinates(-1, 5)));

        assertEquals(1, vehicles.findByCoordinates(1, 2).size());
        assertEquals(1, vehicles.findByCoordinates(-1, -2).size());
        assertEquals(1, vehicles.findByCoordinates(-1, 5).size());
    }


    @Test
    public void shouldFindVehicleByName() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(1, 2)));
        vehicles.addOrUpdate(new Vehicle("two", new Coordinates(-1, -2)));
        vehicles.addOrUpdate(new Vehicle("three", new Coordinates(-1, 5)));

        assertEquals("one", vehicles.findByName("one").getName());
    }

    @Test
    public void shouldFindVehiclesInCertainPosition() {
        VehicleCollection vehicles = new VehicleCollection();
        vehicles.addOrUpdate(new Vehicle("one", new Coordinates(1, 2)));
        vehicles.addOrUpdate(new Vehicle("two", new Coordinates(1, 2)));
        vehicles.addOrUpdate(new Vehicle("three", new Coordinates(-1, 5)));

        assertEquals(2, vehicles.findByCoordinates(1, 2).size());
    }


    @Test
    public void shouldFindInHugeCollectionByName() {
        VehicleCollection vehicles = new VehicleCollection();
        Random rand = new Random();
        for (int i = 0; i < 1000_000; i++) {
            vehicles.addOrUpdate(new Vehicle("Vehicle #" + i, new Coordinates(rand.nextInt((180 + 180) + 1) - 180, rand.nextInt((90 + 90) + 1) - 90)));
        }

        long start = System.currentTimeMillis();
        assertEquals("Vehicle #900000", vehicles.findByName("Vehicle #900000").getName());
        long end = System.currentTimeMillis() - start;
        System.out.println("data searching in 'shouldFindInHugeCollectionByName' took  " + end + "ms"); // just for demo; should be removed
    }

    @Test
    public void shouldFindInHugeCollectionByCoordinate() {
        VehicleCollection vehicles = new VehicleCollection();
        Random rand = new Random();
        for (int i = 0; i < 1000_000; i++) {
            if (i % 1000 == 0) {
                vehicles.addOrUpdate(new Vehicle("Vehicle #" + i, new Coordinates(100, 60)));
            } else {
                vehicles.addOrUpdate(new Vehicle("Vehicle #" + i, new Coordinates(rand.nextInt((180 + 180) + 1) - 180, rand.nextInt((90 + 90) + 1) - 90)));
            }
        }

        long start = System.currentTimeMillis();
        vehicles.addOrUpdate(new Vehicle("Vehicle #900", new Coordinates(100, 60)));
        assertTrue(vehicles.findByCoordinates(100, 60).size() >= 1001);
        long end = System.currentTimeMillis() - start;
        System.out.println("data updating and searching in 'shouldFindInHugeCollectionByCoordinate' took  " + end + "ms"); // just for demo; should be removed
    }


}
