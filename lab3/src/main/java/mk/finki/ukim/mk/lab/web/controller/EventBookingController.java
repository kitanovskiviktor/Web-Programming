package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventBookingController {


    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @PostMapping("/eventbooking")
    public String eventbooking(@RequestParam String selectedEvent,
                               @RequestParam long numTickets,
                               @RequestParam String attendeeName,
                               @RequestParam String attendeeAddress,
                               Model model) {
        EventBooking booking = new EventBooking(selectedEvent, attendeeName, attendeeAddress, numTickets);
        model.addAttribute("booking", booking);
        return "bookingConfirmation";
    }

}
