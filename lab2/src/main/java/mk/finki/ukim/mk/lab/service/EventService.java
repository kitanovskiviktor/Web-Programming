package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    public List<Event> filterByRating(int rating, List<Event> events);
    void save(String name, String description, double popularityScore, long locationId);
    Event findById(long id);
    void update(long eventId, String name, String description, double popularityScore, long locationId);
    void delete(long id);
}
