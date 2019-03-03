package demo.task.vehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
//@EnableRedisRepositories("demo.task.vehicles.repositories")
public class VehiclesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesApplication.class, args);
    }

}
