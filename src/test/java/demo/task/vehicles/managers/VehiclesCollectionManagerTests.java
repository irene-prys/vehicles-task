//package demo.task.vehicles.managers;
//
//import demo.task.vehicles.models.Coordinates;
//import demo.task.vehicles.models.Vehicle;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class VehiclesCollectionManagerTests {
//
//    @Test
//    public void shouldFindByName() {
//        VehiclesCollectionManager manager = new VehiclesCollectionManager();
//        manager.addOrUpdateVehicle(new Vehicle("one", new Coordinates(4, 8)));
//        assertEquals("one", manager.findByName("one").getName());
//    }
//
//    @Test
//    public void shouldFindVehiclesInSquare() {
//        VehiclesCollectionManager manager = new VehiclesCollectionManager();
//        manager.addOrUpdateVehicle(new Vehicle("one", new Coordinates(2, 4)));
//        manager.addOrUpdateVehicle(new Vehicle("two", new Coordinates(3, 5)));
//        manager.addOrUpdateVehicle(new Vehicle("three", new Coordinates(4, 5)));
//        manager.addOrUpdateVehicle(new Vehicle("four", new Coordinates(2, 6)));
//        manager.addOrUpdateVehicle(new Vehicle("five", new Coordinates(4, 6)));
//        manager.addOrUpdateVehicle(new Vehicle("ignored_one", new Coordinates(5, 6)));
//        manager.addOrUpdateVehicle(new Vehicle("ignored_two", new Coordinates(1, 4)));
//        manager.addOrUpdateVehicle(new Vehicle("ignored_three", new Coordinates(4, 7)));
//        manager.addOrUpdateVehicle(new Vehicle("ignored_four", new Coordinates(1, 7)));
//
//        List<Vehicle> vehicles = manager.findInRectangle(4, 2, 6, 4);
//
//        assertEquals(5, vehicles.size());
//        assertEquals(1, vehicles.stream().filter(v -> "two".equals(v.getName())).count());
//        assertEquals(1, vehicles.stream().filter(v -> "five".equals(v.getName())).count());
//        assertEquals(0, vehicles.stream().filter(v -> "ignored_one".equals(v.getName())).count());
//        assertEquals(0, vehicles.stream().filter(v -> "ignored_three".equals(v.getName())).count());
//    }
//}
