package demo.task.vehicles.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("vehicles")
public class Vehicle {
//    @Id
//    private String id;
//    @Indexed
    @Id
    private String name;
    private Point location;

    public Vehicle() {
    }

    public Vehicle(String name, Point location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }


//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "Vehicle: [id=" + id + ", name=" + name + ", location=" + coordinates.getLongitude() + "; " + coordinates.getLatitude() + "]";
//    }
}
