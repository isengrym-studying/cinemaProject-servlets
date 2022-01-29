package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.dao.MovieDao;
import com.example.cinema.model.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

public class GetFilmsCommand implements ActionCommand{
    String page = null;
    @Override
    public String execute(HttpServletRequest req) {
        MovieDao movieDao = MovieDao.getInstance();
        LinkedList<Movie> list = (LinkedList<Movie>) movieDao.getAllMovies();

        req.setAttribute("films", list);

        page = ConfigurationManager.getProperty("path.page.films");

        return page;
    }
}
