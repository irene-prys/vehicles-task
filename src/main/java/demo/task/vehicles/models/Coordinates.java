package demo.task.vehicles.models;

//import org.springframework.data.redis.core.index.GeoIndexed;

public class Coordinates {
    private Integer latitude;
    private Integer longitude;

    public Coordinates(Integer longitude, Integer latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
