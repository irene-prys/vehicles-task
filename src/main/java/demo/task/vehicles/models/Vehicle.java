package demo.task.vehicles.models;


public class Vehicle {
    private String name;
    private Coordinates coordinates;

    public Vehicle() {
    }

    public Vehicle(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
