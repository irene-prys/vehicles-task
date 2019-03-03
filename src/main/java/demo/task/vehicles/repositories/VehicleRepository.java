package demo.task.vehicles.repositories;

import demo.task.vehicles.models.Vehicle;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface VehicleRepository extends CrudRepository <Vehicle, String>{
    List<Vehicle> findByLocationNear(Point point, Distance distance);

}
