package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.Ticket;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.MovieSeanceService;
import com.example.cinema.model.service.TicketService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * The command is responsible for generating the ticket selection page
 *
 */
public class GenerateTicketChoicePage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;


        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
           page = ConfigurationManager.getProperty("path.page.login");
           return page;
        }

        MovieSeanceService msService = MovieSeanceService.getInstance();
        TicketService ticketService = TicketService.getInstance();

        int seanceId = Integer.parseInt(req.getParameter("seanceId"));

        Seance seance = msService.getSeanceById(seanceId);
        Movie movie = msService.getMovieById(seance.getMovie().getId());

        List<Ticket> allTickets = ticketService.getTicketsForSeance(seance.getId());

        req.setAttribute("seance", seance);
        req.setAttribute("ticketList", allTickets);
        req.setAttribute("movie", movie);

        page = ConfigurationManager.getProperty("path.page.buyTicket");

        return page;
    }
}