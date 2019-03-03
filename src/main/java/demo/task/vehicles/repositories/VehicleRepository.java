package demo.task.vehicles.repositories;

import demo.task.vehicles.models.Vehicle;
import org.springframework.data.geo.Box;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface VehicleRepository extends CrudRepository <Vehicle, String>{
//    Optional<Vehicle> findByName(@Param("name") String name);
    Optional<Stream<Vehicle>> findByLocationWithin(Box box);

}
