package mk.finki.ukim.mk.lab.model;

public class Location {

    private long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    public Location() {}

    public Location(String name, String address, String capacity, String description) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
        this.id = (long) (Math.random()*1000);
    }

    public String getName(){return name;}

    public long getId(){return id;}


}
