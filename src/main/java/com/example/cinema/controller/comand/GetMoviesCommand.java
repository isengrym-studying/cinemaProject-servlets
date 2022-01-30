package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.dao.MovieDao;
import com.example.cinema.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class GetMoviesCommand implements ActionCommand{
    String page = null;
    @Override
    public String execute(HttpServletRequest req) {
        MovieDao movieDao = MovieDao.getInstance();
        LinkedList<Movie> list = (LinkedList<Movie>) movieDao.getAllMovies();

        req.setAttribute("movies", list);
        req.setAttribute("pageTitleProperty", "movies.title");
        req.setAttribute("pageHeadlineProperty", "movies.headline");

        page = ConfigurationManager.getProperty("path.page.movies");

        return page;
    }
}