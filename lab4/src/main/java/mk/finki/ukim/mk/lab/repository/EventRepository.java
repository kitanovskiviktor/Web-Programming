package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Repository
public class EventRepository {


    private final LocationRepository locationRepository;
    List<Event> events;

    public EventRepository(LocationRepository locationRepository) {
        events = new ArrayList<>();

        events.add(new Event("Ime Nastan 1", "Opis Nastan 1", 1.1, locationRepository.findAll().get(0)));
        events.add(new Event("Ime Nastan 2", "Opis Nastan 2", 2.2, locationRepository.findAll().get(1)));
        events.add(new Event("Ime Nastan 3", "Opis Nastan 3", 3.3, locationRepository.findAll().get(2)));
        events.add(new Event("Ime Nastan 4", "Opis Nastan 4", 4.4, locationRepository.findAll().get(3)));
        events.add(new Event("Ime Nastan 5", "Opis Nastan 5", 5.5, locationRepository.findAll().get(4)));
        events.add(new Event("Ime Nastan 6", "Opis Nastan 6", 6.6, locationRepository.findAll().get(0)));
        events.add(new Event("Ime Nastan 7", "Opis Nastan 7", 7.7, locationRepository.findAll().get(1)));
        events.add(new Event("Ime Nastan 8", "Opis Nastan 8", 8.8, locationRepository.findAll().get(2)));
        events.add(new Event("Ime Nastan 9", "Opis Nastan 9", 9.9, locationRepository.findAll().get(3)));
        events.add(new Event("Ime Nastan 10", "Opis Nastan 10", 10,locationRepository.findAll().get(4)));
        this.locationRepository = locationRepository;
    }

    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEvents(String text){
        return events.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Event findById(long id){
        return events.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

}
