package demo.task.vehicles.repositories;

import demo.task.vehicles.models.Vehicle;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<List<Vehicle>> findByLocationWithin(Box box);

}
