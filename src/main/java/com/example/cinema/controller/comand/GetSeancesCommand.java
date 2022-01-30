package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.dao.SeanceDao;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public class GetSeancesCommand implements ActionCommand {
    String page = null;

    @Override
    public String execute(HttpServletRequest req) {
        SeanceDao seanceDao = SeanceDao.getInstance();
        MovieSeanceService service = MovieSeanceService.getInstance();

        List<Seance> seancesList = seanceDao.getAllSeances();
        List<Movie> moviesList = service.getUniqueMovies(seancesList);

//        req.setAttribute("seances", seancesList);
        req.setAttribute("movies", moviesList);
        req.setAttribute("pageTitleProperty", "seances.title");
        req.setAttribute("pageHeadlineProperty", "seances.headline");
        page = ConfigurationManager.getProperty("path.page.movies");

        return page;
    }
}
