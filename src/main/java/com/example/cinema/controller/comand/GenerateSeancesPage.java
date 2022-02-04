package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The command that is responsible for getting and sending back all films, that have active seances
 *
 */
public class GenerateSeancesPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();

        List<Seance> seancesList = service.getAllSeances();
        List<Seance> futureSeances = service.getOnlyFutureSeances(seancesList);
        List<Movie> moviesList = service.getUniqueMovies(futureSeances);

//        req.setAttribute("seances", seancesList);
        req.setAttribute("movies", moviesList);
        req.setAttribute("pageTitleProperty", "seances.title");
        req.setAttribute("pageHeadlineProperty", "seances.headline");

        page = ConfigurationManager.getProperty("path.page.movies");
        ActionCommand.pageAdress(req);

        return page;
    }
}
