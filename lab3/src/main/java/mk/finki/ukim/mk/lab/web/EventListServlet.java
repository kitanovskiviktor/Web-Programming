package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "eventListServlet", urlPatterns = "/home")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("sessionCheck", "active");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String search = req.getParameter("search");
        int rating = req.getParameter("rating") != null ? Integer.parseInt(req.getParameter("rating")) : 1;

        if(search != null && search.length() > 0) {
            context.setVariable("search", search);
            context.setVariable("searchRating", rating);

           // context.setVariable("events", eventService.filterByRating(rating, eventService.searchEvents(search)));

        }else {
            context.setVariable("events", eventService.listAll());

        }

        templateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("name");
        String rating = req.getParameter("rating");

        if(rating == "") rating = "1";


        resp.sendRedirect("/home?search=" + search + "&rating=" + rating);
    }
}
