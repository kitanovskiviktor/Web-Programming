package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.impl.EventServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.LocationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/events")
public class EventController {
    private final EventServiceImpl eventService;
    private final LocationServiceImpl locationService;

    public EventController(EventServiceImpl eventService, LocationServiceImpl locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req) {
        List<Location> locations = locationService.findAll();
        if (locations == null) {
            locations = List.of();
        }
        List<Event> eventList = eventService.listAll();
        if (eventList == null) {
            eventList = List.of();
        }
        String searchName = req.getParameter("searchName");
        String minRating = req.getParameter("minRating");
        if (searchName != null && minRating != null && !minRating.isEmpty()) {
            eventList = eventService.searchEvents(searchName).stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (minRating != null && !minRating.isEmpty()) {
            eventList = eventService.listAll().stream()
                    .filter(event -> event.getPopularityScore() >= Double.parseDouble(minRating))
                    .toList();
        } else if (searchName != null) {
            eventList = eventService.searchEvents(searchName);
        } else {
            eventList = eventService.listAll();
        }

        if (eventList.isEmpty()) {
            model.addAttribute("events", null);
        } else {
            model.addAttribute("events", eventList);
        }

        if (locations.isEmpty()) {
            model.addAttribute("locations", null);
        } else {
            model.addAttribute("locations", locations);
        }
        return "listEvents";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model) {
        List<Location> locationList = locationService.findAll();
        if (locationList == null) {
            locationList = List.of();
        }
        model.addAttribute("locations", locationList);
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @GetMapping("/edit-form/{id}")
    public String editEventPage(Model model, @PathVariable Long id) {
        Event event = eventService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
        List<Location> locationList = locationService.findAll();
        if (locationList == null) {
            locationList = List.of();
        }
        model.addAttribute("locations", locationList);
        model.addAttribute("event", event);
        return "addEvent";
    }

    @GetMapping("/event-details/{id}")
    public String eventDetailsPage(Model model, @PathVariable Long id) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            model.addAttribute("event", event);
            return "eventDetails";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/by-location")
    public String getEventsByLocation(@RequestParam Long locationId, Model model) {
        List<Event> events = eventService.findAllByLocationId(locationId);
        model.addAttribute("events", events);
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

    @PostMapping("/change-rating/{id}")
    public String changeEventRating(@PathVariable Long id, HttpServletRequest req) {
        if (this.eventService.findById(id).isPresent()) {
            if (req.getParameter("increment") != null) {
                this.eventService.changeEventRating(id, req.getParameter("increment"));
            } else if (req.getParameter("decrease") != null) {
                this.eventService.changeEventRating(id, req.getParameter("decrease"));
            }
            return "redirect:/events/event-details/" + id;
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/add")
    public String saveEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam Long location
    ) {
        if (id != null) {
            this.eventService.update(id, name, description, popularityScore, location);
        } else {
            this.eventService.save(name, description, popularityScore, location);
        }
        return "redirect:/events";
    }
}
