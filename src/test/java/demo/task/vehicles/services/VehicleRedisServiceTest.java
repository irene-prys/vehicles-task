//package demo.task.vehicles.services;
//
//import demo.task.vehicles.managers.VehiclesManager;
//import demo.task.vehicles.models.Vehicle;
//import demo.task.vehicles.services.VehicleServiceImpl;
//import demo.task.vehicles.utils.GeoCalculator;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.geo.Point;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class VehicleRedisServiceTest {
//    @InjectMocks
//    private VehicleServiceImpl vehicleService;
//    @Mock
//    private VehiclesManager vehiclesManager;
//
//    @Test
//    public void shouldFindOnlyDataInRectangle() {
//        double topLeftLng = 0;
//        double topLeftLat = 0;
//        double bottomRightLng = 6;
//        double bottomRightLat = 4;
//
//        double distance = GeoCalculator.calculateDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        Vehicle vehicle1 = new Vehicle("one", new Point(1, 3));
//        Vehicle vehicle2 = new Vehicle("two", new Point(5, 3));
//        Vehicle vehicle3 = new Vehicle("three", new Point(5, 1));
//        Vehicle ignoredVehicle1 = new Vehicle("ignored_one", new Point(5, 10));
//        Optional<List<Vehicle>> vehicles = Optional.of(Arrays.asList(vehicle1, vehicle2, vehicle3, ignoredVehicle1));
//        Mockito.when(vehiclesManager.findInSquare(topLeftLng, topLeftLat, distance)).thenReturn(vehicles);
//
//        List<Vehicle> foundVehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        assertEquals(3, foundVehicles.size());
//
//        assertEquals(0, foundVehicles.stream().filter(v -> "ignored_one".equals(v.getName())).count());
//    }
//
//    @Test
//    public void shouldFindIfCoordinatesEqualToEndPoint() {
//        double topLeftLng = 0;
//        double topLeftLat = 0;
//        double bottomRightLng = 6;
//        double bottomRightLat = 4;
//
//        double distance = GeoCalculator.calculateDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        Vehicle vehicle1 = new Vehicle("one", new Point(6, 4));
//        Optional<List<Vehicle>> vehicles = Optional.of(Arrays.asList(vehicle1));
//        Mockito.when(vehiclesManager.findInSquare(topLeftLng, topLeftLat, distance)).thenReturn(vehicles);
//
//        List<Vehicle> foundVehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        assertEquals(1, foundVehicles.size());
//    }
//
//    @Test
//    public void shouldFindIfCoordinatesEqualToStartPoint() {
//        double topLeftLng = 0;
//        double topLeftLat = 0;
//        double bottomRightLng = 6;
//        double bottomRightLat = 4;
//
//        double distance = GeoCalculator.calculateDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        Vehicle vehicle1 = new Vehicle("one", new Point(0, 0));
//        Optional<List<Vehicle>> vehicles = Optional.of(Arrays.asList(vehicle1));
//        Mockito.when(vehiclesManager.findInSquare(topLeftLng, topLeftLat, distance)).thenReturn(vehicles);
//
//        List<Vehicle> foundVehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        assertEquals(1, foundVehicles.size());
//    }
//
//    @Test
//    public void shouldFindWithNegativeCoordinates() {
//        double topLeftLng = -3;
//        double topLeftLat = -4;
//        double bottomRightLng = 6;
//        double bottomRightLat = 4;
//
//        double distance = GeoCalculator.calculateDistance(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        Vehicle vehicle1 = new Vehicle("one", new Point(-1, -1));
//        Optional<List<Vehicle>> vehicles = Optional.of(Arrays.asList(vehicle1));
//        Mockito.when(vehiclesManager.findInSquare(topLeftLng, topLeftLat, distance)).thenReturn(vehicles);
//
//        List<Vehicle> foundVehicles = vehicleService.findInRectangle(topLeftLng, topLeftLat, bottomRightLng, bottomRightLat);
//        assertEquals(1, foundVehicles.size());
//    }
//}
