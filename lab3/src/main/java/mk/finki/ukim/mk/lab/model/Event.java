package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name="event")
public class Event {

    String name;
    String description;
    double popularityScore;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;
    private boolean isRatingManipulated;

    public Event(String name, String description, double popularityScore, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = location;
        this.isRatingManipulated = false;
    }

    public Event() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    public Location getLocation(){
        return location;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setPopularityScore(double popularityScore){
        this.popularityScore = popularityScore;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public void setRatingManipulated() {
        this.isRatingManipulated = true;
    }

    public boolean isRatingManipulated() {
        return isRatingManipulated;
    }




}
