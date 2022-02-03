package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

/**
 * The command that is responsible for getting and sending back all movies
 *
 */
public class GenerateMoviePage implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        ActionCommand.pageAdress(req);
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();
        LinkedList<Movie> list = (LinkedList<Movie>) service.getAllMovies();

        req.setAttribute("movies", list);
        req.setAttribute("pageTitleProperty", "movies.title");
        req.setAttribute("pageHeadlineProperty", "movies.headline");

        page = ConfigurationManager.getProperty("path.page.movies");

        return page;
    }
}
