package demo.task.vehicles.managers;

import demo.task.vehicles.models.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleManagerTest {

    @Autowired
    private VehiclesManager vehiclesManager;

    @Test
    public void shouldAddDataAndFindIt() {
        vehiclesManager.addOrUpdateVehicle(new Vehicle("one", new Point(1, 2)));
        assertTrue(vehiclesManager.findById("one").isPresent());
    }

    @Test
    public void shouldFindInSquare() {
        vehiclesManager.addOrUpdateVehicle(new Vehicle("one", new Point(1, 2)));
        vehiclesManager.addOrUpdateVehicle(new Vehicle("two", new Point(4, 10)));
        vehiclesManager.addOrUpdateVehicle(new Vehicle("three", new Point(-1, -2)));
        vehiclesManager.addOrUpdateVehicle(new Vehicle("ignored_one", new Point(-10, -12)));
        vehiclesManager.addOrUpdateVehicle(new Vehicle("ignored_two", new Point(15, 22)));

        Optional<List<Vehicle>> vehicles = vehiclesManager.findInSquare(-2, -3, 10, 20);

        assertTrue(vehicles.isPresent());
        assertEquals(3, vehicles.get().size());
        assertEquals(0, vehicles.get().stream().filter(v -> "ignored_one".equals(v.getId())).count());
        assertEquals(0, vehicles.get().stream().filter(v -> "ignored_two".equals(v.getId())).count());
        assertEquals(1, vehicles.get().stream().filter(v -> "one".equals(v.getId())).count());
        assertEquals(1, vehicles.get().stream().filter(v -> "two".equals(v.getId())).count());
        assertEquals(1, vehicles.get().stream().filter(v -> "three".equals(v.getId())).count());
    }

    @Test
    public void shouldFindInSquareWithPointEqualToStart() {
        vehiclesManager.addOrUpdateVehicle(new Vehicle("one", new Point(1, 2)));

        Optional<List<Vehicle>> vehicles = vehiclesManager.findInSquare(1, 2, 10, 20);

        assertTrue(vehicles.isPresent());
        assertEquals(1, vehicles.get().size());
        assertEquals(1, vehicles.get().stream().filter(v -> "one".equals(v.getId())).count());
    }

    @Test
    public void shouldFindInSquareWithPointEqualToEnd() {
        vehiclesManager.addOrUpdateVehicle(new Vehicle("one", new Point(10, 20)));

        Optional<List<Vehicle>> vehicles = vehiclesManager.findInSquare(1, 2, 10, 20);

        assertTrue(vehicles.isPresent());
        assertEquals(1, vehicles.get().size());
        assertEquals(1, vehicles.get().stream().filter(v -> "one".equals(v.getId())).count());
    }

}
