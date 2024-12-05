package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name="location")
public class Location {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String name;
    private String address;
    private String capacity;
    private String description;
    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location() {}

    public Location(String name, String address, String capacity, String description) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
      //  this.id = (long) (Math.random()*1000);
    }


}
