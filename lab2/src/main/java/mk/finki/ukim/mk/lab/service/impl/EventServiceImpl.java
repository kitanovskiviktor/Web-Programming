package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public List<Event> filterByRating(int rating, List<Event> events) {
        return events.stream()
                .filter(e -> e.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public void save(String name, String description, double popularityScore, long locationId){
        Location location = locationRepository.findAll()
                .stream()
                .filter(loc -> loc.getId() == locationId)
                .findFirst()
                .orElse(null);

        if(location != null){
            Event event = new Event(name, description, popularityScore, location);
            eventRepository.findAll().add(event);
        }
    }

    @Override
    public Event findById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void update(long eventId, String name, String description, double popularityScore, long locationId) {
        Event event = eventRepository.findById(eventId);
        if(event != null){
            Location location = locationRepository.findAll()
                    .stream()
                    .filter(loc -> loc.getId() == locationId)
                    .findFirst()
                    .orElse(null);

                if(location != null){
                    event.setName(name);
                    event.setDescription(description);
                    event.setPopularityScore(popularityScore);
                    event.setLocation(location);
                }
        }
    }

    @Override
    public void delete(long eventId){
        Event event = eventRepository.findById(eventId);
        if(event != null){
            eventRepository.findAll().remove(event);
        }
    }
}
