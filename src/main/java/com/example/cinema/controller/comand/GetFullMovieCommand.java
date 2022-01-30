package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.dao.MovieDao;
import com.example.cinema.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;

public class GetFullMovieCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieDao movieDao = MovieDao.getInstance();
        int movieId = Integer.parseInt(req.getParameter("movieId"));

        Movie movie = movieDao.getMovieById(movieId);
        req.setAttribute("movie", movie);
        req.setAttribute("durationMin",movie.getDuration().getSeconds()/60);

        page = ConfigurationManager.getProperty("path.page.movie");

        return page;
    }


}
