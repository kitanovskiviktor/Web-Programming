package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final LocationRepository locationRepository;

    public EventController(EventService eventService, LocationRepository locationRepository) {
        this.eventService = eventService;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        List<Event> events = eventService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("error", error);
        return "listEvents";
    }

    @GetMapping("/events/add")
    public String showAddEventForm(Model model){
        model.addAttribute("locations", locationRepository.findAll());
        return "addEvent";
    }

    @PostMapping("/events/add")
    public String saveEvent(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam long locationId){
        eventService.save(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{eventId}")
    public String showEditForm(@PathVariable long eventId, Model model){
        Event event = eventService.findById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("locations", locationRepository.findAll());
        return "editEvent";

    }

    @PostMapping("/events/edit/{eventId}")
    public String editEvent(@PathVariable long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam long locationId){
        eventService.update(eventId, name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @PostMapping("/events/delete")
    public String deleteEvent(@RequestParam long eventId){
        eventService.delete(eventId);
        return "redirect:/events";
    }


}
