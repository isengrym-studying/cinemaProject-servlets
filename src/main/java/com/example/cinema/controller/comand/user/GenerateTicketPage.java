package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The command is responsible for generating definite ticket page with
 * buy confirmation
 */
public class GenerateTicketPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        MovieSeanceService service = MovieSeanceService.getInstance();

        int rowId = Integer.parseInt(req.getParameter("rowId"));
        int placeId = Integer.parseInt(req.getParameter("placeId"));
        int seanceId = Integer.parseInt(req.getParameter("seanceId"));

        Seance seance = service.getSeanceById(seanceId);
        Movie movie = seance.getMovie();

        HttpSession session = req.getSession();
        session.setAttribute("rowId", rowId);
        session.setAttribute("placeId", placeId);
        session.setAttribute("seance", seance);
        req.setAttribute("movie", movie);

        page = ConfigurationManager.getProperty("path.page.ticketPage");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}
