package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="eventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet implements HttpSessionListener {
    private final SpringTemplateEngine templateEngine;
    private final EventBookingService eventBookingService;
    public EventBooking booking;

    public EventBookingServlet( SpringTemplateEngine templateEngine, EventBookingService eventBookingService) {
        this.templateEngine = templateEngine;
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("sessionCheck", "active");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("booking", booking);

        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("selectedEvent"));
        booking = new EventBooking(req.getParameter("selectedEvent"), req.getLocalName(), req.getRemoteAddr(), (long) Integer.parseInt(req.getParameter("numTickets")));
        eventBookingService.placeBooking(req.getParameter("selectedEvent"), req.getLocalName(), req.getRemoteAddr(),Integer.parseInt(req.getParameter("numTickets")));
        System.out.println(booking.getNumberOfTickets());

        resp.sendRedirect("/eventBooking");

    }
}
