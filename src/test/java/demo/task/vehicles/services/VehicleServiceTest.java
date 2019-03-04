package demo.task.vehicles.services;

import demo.task.vehicles.managers.VehiclesManager;
import demo.task.vehicles.models.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
    @InjectMocks
    private VehicleServiceImpl vehicleService;
    @Mock
    private VehiclesManager vehiclesManager;

    @Test
    public void shouldReturnTheDataFromManagerForFindInSquare() {
        double topLeftLng = 0;
        double topLeftLat = 0;
        double bottomRightLng = 6;
        double bottomRightLat = 4;

        Vehicle vehicle1 = new Vehicle("one", new Point(1, 3));
        Vehicle vehicle2 = new Vehicle("two", new Point(5, 3));
        Vehicle vehicle3 = new Vehicle("three", new Point(5, 1));
        Optional<List<Vehicle>> vehicles = Optional.of(Arrays.asList(vehicle1, vehicle2, vehicle3));
        Mockito.when(vehiclesManager.findInSquare(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat)).thenReturn(vehicles);

        List<Vehicle> foundVehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
        assertEquals(3, foundVehicles.size());
    }

    @Test
    public void shouldReturnTheDataFromManagerForFindByName() {
        Vehicle vehicle1 = new Vehicle("one", new Point(1, 3));
        Mockito.when(vehiclesManager.findById("one")).thenReturn(Optional.of(vehicle1));

        Optional<Vehicle> foundVehicle = vehicleService.findByName("one");
        assertTrue(foundVehicle.isPresent());
    }

    @Test
    public void shouldCallSaveInManager() {
        Vehicle vehicle1 = new Vehicle("one", new Point(1, 3));
        Mockito.when(vehiclesManager.addOrUpdateVehicle(vehicle1)).thenReturn(vehicle1);

        Vehicle foundVehicle = vehicleService.create(vehicle1);
        assertEquals("one", foundVehicle.getId());
    }
}

