package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GenerateTicketPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
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


        return page;
    }
}
