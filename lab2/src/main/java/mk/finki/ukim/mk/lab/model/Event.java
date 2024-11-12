package mk.finki.ukim.mk.lab.model;

public class Event {

    String name;
    String description;
    double popularityScore;
    private long id;
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = location;
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

    public long getId(){
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




}
